package controller.developre;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import pojo.AppCategory;
import pojo.AppInfo;
import pojo.AppVersion;
import pojo.DataDictionary;
import pojo.DevUser;
import service.DataDictionary.DataDictionaryService;
import service.appCategory.AppCategoryService;
import service.appVersion.AppVersionService;
import service.appinfo.AppinfoService;
import toolls.SysInterceptor;

@Controller
@RequestMapping("/appinfo")
public class AppinfoController extends SysInterceptor {
	private Logger logger=Logger.getLogger(getClass());
	@Autowired
	private AppinfoService appinfoService;
	@Autowired
	private DataDictionaryService dataDictionaryService;
	@Autowired
	private AppCategoryService appCategoryService;
	@Autowired 
	private AppVersionService appVersionService;
	/**
	 * 分页查询
	 */
	@RequestMapping("/list")
	public String appinfoList(HttpServletRequest request){
		//获取信息
		String querySoftwareName=request.getParameter("querySoftwareName");
		String queryStatus=request.getParameter("queryStatus");
		String queryFlatformId=request.getParameter("queryFlatformId");
		String categoryLevel1List=request.getParameter("aa1");
		System.out.println(categoryLevel1List+".0+................");
		String categoryLevel2List=request.getParameter("queryCategoryLevel2");
		String categoryLevel3List=request.getParameter("queryCategoryLevel3");
		List<AppCategory> categoryLevel2Lists=null;
		List<AppCategory> categoryLevel3Lists=null;
		if (categoryLevel2List!=null) {
			 categoryLevel2Lists=appCategoryService.getAppCategoryByparentId(Integer.parseInt(categoryLevel1List));
		}
		if (categoryLevel3List!=null) {
			 categoryLevel3Lists=appCategoryService.getAppCategoryByparentId(Integer.parseInt(categoryLevel2List));
		}
		logger.debug(querySoftwareName+"-----"+queryStatus+"----"+queryFlatformId+"----"+categoryLevel1List+"a----"+categoryLevel2List+"----"+categoryLevel3List);
		Map<String, Object> appMap=new HashMap<String, Object>();
		appMap.put("softwareName", querySoftwareName);
		appMap.put("status", queryStatus);
		appMap.put("flatformId", queryFlatformId);
		appMap.put("categoryLevel1", categoryLevel1List);
		appMap.put("categoryLevel2", categoryLevel2List);
		appMap.put("categoryLevel3", categoryLevel3List);
		//获取总数
		int appinfoCount=appinfoService.getAppInfoCount(appMap);
		logger.debug(appinfoCount+"--appinfo的总数");
		//获取总页数
		int pageCount=appinfoCount%4==0?(appinfoCount/4):(appinfoCount/4+1);
		logger.debug(pageCount+"---总页数");
		//获取当前页面
		String cur=request.getParameter("pageIndex");
		if (cur==null) {
			cur="1";
		}
		appMap.put("cur", (Integer.parseInt(cur)-1)*4);
		appMap.put("curSize", 4);
		//获取数据
		List<AppInfo> appInfos=appinfoService.getAppInfoList(appMap);
		logger.debug(appInfos.size()+"---数据的数量");
		//获取所有的状态
		List<DataDictionary> dataDictionaries=dataDictionaryService.getstatusList();
		//获取所有的所属平台
		request.setAttribute("flatFormList", dataDictionaryService.getflatformList("APP_FLATFORM"));
		//获取一级分类
		request.setAttribute("categoryLevel1List", appCategoryService.getAppCategoryByparentIdNUll());
		request.setAttribute("categoryLevel2Lists", categoryLevel2Lists);
		request.setAttribute("categoryLevel3Lists", categoryLevel3Lists);
		request.setAttribute("statusList", dataDictionaries);
		request.setAttribute("appInfoList", appInfos);
		request.setAttribute("cur", cur);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("queryStatus", queryStatus);
		request.setAttribute("querySoftwareName", querySoftwareName);
		request.setAttribute("queryFlatformId", queryFlatformId);
		request.setAttribute("categoryLevel1", categoryLevel1List);
		request.setAttribute("categoryLevel2", categoryLevel2List);
		request.setAttribute("categoryLevel3", categoryLevel3List);
		request.setAttribute("num", appInfos.size());
		return "developer/appinfolist";
	}
	/**
	 * 添加数据的请求
	 */
	@RequestMapping("/appinfoadd")
	public String appinfoadd(){
		return "developer/appinfoadd";
	}
	/**
	 * 添加数据
	 */
	@RequestMapping("/appinfoaddsave")
	public String appinfoaddsave(AppInfo appInfo,HttpServletRequest request,
			HttpSession session,
			@RequestParam(value="a_logoPicPath",required=false)MultipartFile attach){
		//用于保存在数据库的的消息
		String logoPicPath=null;
		String logoLocPath=null;
		//判断文件是为空
		if (!attach.isEmpty()) {
			//获取文件名
			String fileName=attach.getOriginalFilename();
			//创建文件上传的路径
			logoLocPath=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles"+File.separator+fileName);
			logoPicPath="/appinfo/WebContent/statics"+File.separator+"uploadfiles/"+fileName;
			String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			System.out.println(request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles"));
						//获取后缀
			String prefix=FilenameUtils.getExtension(fileName);
			System.out.println(prefix+"--后缀");
			
			int fileSize=900000000;
			if (attach.getSize()>fileSize) {//文件太大
				request.setAttribute("uploadFileError","文件大小不得超过900000KB");
				return "developer/appinfoadd";
			}else if (prefix.equalsIgnoreCase("jpg")|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")|| prefix.equals("pneg")) {
				File file=new File(path,fileName);
				//判断文件路径是否存在，不存在就创建
				if (!file.exists()) {
					file.mkdirs();
				}
				//保存
				try {
					attach.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("uploadFileError","上传失败");
					return "developer/appinfoadd";
				}
				
			}else{
				request.setAttribute("uploadFileError","上传的文件格式不正确");
				return "developer/appinfoadd";
			}
		}
		//添加信息到数据库的操作
		appInfo.setLogoPicPath(logoPicPath);
		appInfo.setLogoLocPath(logoLocPath);
		appInfo.setCreatedBy(((DevUser)session.getAttribute("devUser")).getId());
		appInfo.setCreationDate(new Date());
		int i=appinfoService.addApp(appInfo);
		if (i>0) {
			return "redirect:/appinfo/list";
		}else{
			request.setAttribute("e","添加失败");
			return "developer/appinfoadd";
		}
	}
	/**
	 * 修改的请求
	 */
	@RequestMapping("/appinfomodify")
	public String appinfomodify(HttpServletRequest request){
		String id=request.getParameter("id");
		//获取当前id的信息
		AppInfo appInfo=appinfoService.getAppInfoById(Integer.parseInt(id));
		request.setAttribute("appInfo", appInfo);
		return "developer/appinfomodify";
	}
	/**
	 * 修改
	 */
	@RequestMapping("/appinfomodifysave")
	public String appinfomodifysave(AppInfo appInfo,
			HttpServletRequest request,HttpSession session,
			@RequestParam(value="attach",required=false) MultipartFile attach){
		System.out.println("appinfomodifysave..");
		
		//获取id
		String id=request.getParameter("id");
		//判断文件是为空
		if (!attach.isEmpty()) {
			//用于保存在数据库中的数据
			String logoPicPath=null;
			String logoLocPath=null;
			//获取文件名
			String fileName=attach.getOriginalFilename();
			//创建文件上传的路径
			logoPicPath="/appinfo/WebContent/statics/uploadfiles/"+fileName;
			logoLocPath=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles/"+fileName);
			String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			System.out.println();
			System.out.println(logoLocPath+"\n"+logoPicPath);
			System.out.println(request.getSession().getServletContext().getRealPath("uploadfiles"));
						//获取后缀
			String prefix=FilenameUtils.getExtension(fileName);
			System.out.println(prefix+"--后缀");
			
			int fileSize=900000000;
			if (attach.getSize()>fileSize) {//文件太大
				request.setAttribute("uploadFileError","文件大小不得超过900000KB");
				return "redirect:/appinfo/appinfomodify?id="+id;
			}else if (prefix.equalsIgnoreCase("jpg")|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")|| prefix.equals("pneg")) {
				File file=new File(path,fileName);
				//判断文件路径是否存在，不存在就创建
				if (!file.exists()) {
					file.mkdirs();
				}
				//保存
				try {
					attach.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("uploadFileError","上传失败");
					return "redirect:/appinfo/appinfomodify?id="+id;
				}
				appInfo.setLogoPicPath(logoPicPath);
				appInfo.setLogoLocPath(logoLocPath);
			}else{
				request.setAttribute("uploadFileError","上传的文件格式不正确");
				return "redirect:/appinfo/appinfomodify?id="+id;
			}
		}else {
			appInfo.setLogoPicPath("");
			appInfo.setLogoLocPath("");
		}
		//修改的操作
		
		appInfo.setModifyBy(((DevUser)session.getAttribute("devUser")).getId());
		
		appInfo.setDevId(((DevUser)session.getAttribute("devUser")).getId());
		appInfo.setModifyDate(new Date());
		appInfo.setUpdateDate(new Date());
		int i=appinfoService.modifyAppInfo(appInfo);
		if (i>0) {
			return "redirect:/appinfo/list";
		}else{
			request.setAttribute("e","修改失败");
			return "redirect:/appinfo/appinfomodify?id="+id;
		}
	}
	/**
	 * 删除图片·
	 */
	@RequestMapping("/delfiletup.json")
	@ResponseBody
	public Object delfiletup(HttpServletRequest request){
		//获取id
		String id=request.getParameter("id");
		System.out.println(id+".....");
		Map<String, String> map=new HashMap<String,String>();
		AppInfo appInfo=appinfoService.getAppInfoById(Integer.parseInt(id));
		System.out.println(appInfo.getLogoLocPath());
		AppInfo appInfo2 = new AppInfo();
		appInfo2.setLogoLocPath(null);
		appInfo2.setLogoPicPath(null);
		appInfo2.setId(Integer.parseInt(id));
		int i=appinfoService.modifyAppInfo(appInfo2);
		System.err.println(i+"............");
		if (i>0) {
			File file=new File(appInfo.getLogoLocPath());//删除文件
			System.out.println(appInfo.getLogoLocPath());
			if (file.delete()) {
				map.put("result", "success");
			}
		}else{
			map.put("result", "failed");
		}
		return JSON.toJSONString(map);
	}
	/**
	 * 查看
	 */
	@RequestMapping("/appview/{id}")
	public String appview(@PathVariable String id,HttpServletRequest request){
		//查询数据
		AppInfo appInfo=appinfoService.getAppInfoById(Integer.parseInt(id));
		request.setAttribute("appInfo", appInfo);
		/**
		 * 根据id查询版本
		 */
		List<AppVersion> appVersionList=appVersionService.getAppVersionList(Integer.parseInt(id));
		System.out.println(appVersionList.size());
		request.setAttribute("appVersionList", appVersionList);
		return "developer/appinfoview";
	}
	/**
	 * 删除app
	 */
	@RequestMapping("delapp.json")
	@ResponseBody
	public String delapp(HttpServletRequest request){
		System.out.println("............");
		//获取id
		String id=request.getParameter("id");
		Map<String, String> map=new HashMap<String,String>();
		//先删除版本
		int i=appVersionService.delAppVesionByAppId(Integer.parseInt(id));
		if (i>0 || i==0) {
			int num=appinfoService.delApp(Integer.parseInt(id));
			map.put("delResult", "true");
		}else{
			map.put("delResult", "false");
		}
		return JSON.toJSONString(map);
	}
	/**
	 * 判断apkName是否存在
	 */
	@RequestMapping("/apkexist.json")
	@ResponseBody
	public Object apkexist(HttpServletRequest request){
		//获取参数
		String akpName=request.getParameter("APKName");
		Map<String, String> map=new HashMap<String,String>();
		if (akpName==null) {
			map.put("APKName", "empty");
		}
		
		//调用方法
		AppInfo appInfo=appinfoService.getAppInfoByapkName(akpName);
		if (appInfo!=null) {
			map.put("APKName", "exist");
		}else{
			map.put("APKName", "noexist");
		}
		return JSON.toJSONString(map);
	}
	
	/**
	 * 上架和下架
	 */
	@RequestMapping("/sale.json")
	@ResponseBody
	public Object sale(HttpServletRequest request){
		//获取信息
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		Map<String, String> map=new HashMap<String,String>();
		if (status.equals("4")) {
			int i=appinfoService.modifystatus(4, Integer.parseInt(id));
			if (i>0) {
				map.put("resultMsg", "success");
			}else{
				map.put("resultMsg", "");
			}
		}else{
			int i=appinfoService.modifystatus(5, Integer.parseInt(id));
			if (i>0) {
				map.put("resultMsg", "success");
			}else{
				map.put("resultMsg", "error");
			}
		}
		
		return JSON.toJSONString(map);
	}
	
	
}
