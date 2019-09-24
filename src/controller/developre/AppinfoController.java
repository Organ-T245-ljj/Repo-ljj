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
	 * ��ҳ��ѯ
	 */
	@RequestMapping("/list")
	public String appinfoList(HttpServletRequest request){
		//��ȡ��Ϣ
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
		//��ȡ����
		int appinfoCount=appinfoService.getAppInfoCount(appMap);
		logger.debug(appinfoCount+"--appinfo������");
		//��ȡ��ҳ��
		int pageCount=appinfoCount%4==0?(appinfoCount/4):(appinfoCount/4+1);
		logger.debug(pageCount+"---��ҳ��");
		//��ȡ��ǰҳ��
		String cur=request.getParameter("pageIndex");
		if (cur==null) {
			cur="1";
		}
		appMap.put("cur", (Integer.parseInt(cur)-1)*4);
		appMap.put("curSize", 4);
		//��ȡ����
		List<AppInfo> appInfos=appinfoService.getAppInfoList(appMap);
		logger.debug(appInfos.size()+"---���ݵ�����");
		//��ȡ���е�״̬
		List<DataDictionary> dataDictionaries=dataDictionaryService.getstatusList();
		//��ȡ���е�����ƽ̨
		request.setAttribute("flatFormList", dataDictionaryService.getflatformList("APP_FLATFORM"));
		//��ȡһ������
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
	 * ������ݵ�����
	 */
	@RequestMapping("/appinfoadd")
	public String appinfoadd(){
		return "developer/appinfoadd";
	}
	/**
	 * �������
	 */
	@RequestMapping("/appinfoaddsave")
	public String appinfoaddsave(AppInfo appInfo,HttpServletRequest request,
			HttpSession session,
			@RequestParam(value="a_logoPicPath",required=false)MultipartFile attach){
		//���ڱ��������ݿ�ĵ���Ϣ
		String logoPicPath=null;
		String logoLocPath=null;
		//�ж��ļ���Ϊ��
		if (!attach.isEmpty()) {
			//��ȡ�ļ���
			String fileName=attach.getOriginalFilename();
			//�����ļ��ϴ���·��
			logoLocPath=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles"+File.separator+fileName);
			logoPicPath="/appinfo/WebContent/statics"+File.separator+"uploadfiles/"+fileName;
			String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			System.out.println(request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles"));
						//��ȡ��׺
			String prefix=FilenameUtils.getExtension(fileName);
			System.out.println(prefix+"--��׺");
			
			int fileSize=900000000;
			if (attach.getSize()>fileSize) {//�ļ�̫��
				request.setAttribute("uploadFileError","�ļ���С���ó���900000KB");
				return "developer/appinfoadd";
			}else if (prefix.equalsIgnoreCase("jpg")|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")|| prefix.equals("pneg")) {
				File file=new File(path,fileName);
				//�ж��ļ�·���Ƿ���ڣ������ھʹ���
				if (!file.exists()) {
					file.mkdirs();
				}
				//����
				try {
					attach.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("uploadFileError","�ϴ�ʧ��");
					return "developer/appinfoadd";
				}
				
			}else{
				request.setAttribute("uploadFileError","�ϴ����ļ���ʽ����ȷ");
				return "developer/appinfoadd";
			}
		}
		//�����Ϣ�����ݿ�Ĳ���
		appInfo.setLogoPicPath(logoPicPath);
		appInfo.setLogoLocPath(logoLocPath);
		appInfo.setCreatedBy(((DevUser)session.getAttribute("devUser")).getId());
		appInfo.setCreationDate(new Date());
		int i=appinfoService.addApp(appInfo);
		if (i>0) {
			return "redirect:/appinfo/list";
		}else{
			request.setAttribute("e","���ʧ��");
			return "developer/appinfoadd";
		}
	}
	/**
	 * �޸ĵ�����
	 */
	@RequestMapping("/appinfomodify")
	public String appinfomodify(HttpServletRequest request){
		String id=request.getParameter("id");
		//��ȡ��ǰid����Ϣ
		AppInfo appInfo=appinfoService.getAppInfoById(Integer.parseInt(id));
		request.setAttribute("appInfo", appInfo);
		return "developer/appinfomodify";
	}
	/**
	 * �޸�
	 */
	@RequestMapping("/appinfomodifysave")
	public String appinfomodifysave(AppInfo appInfo,
			HttpServletRequest request,HttpSession session,
			@RequestParam(value="attach",required=false) MultipartFile attach){
		System.out.println("appinfomodifysave..");
		
		//��ȡid
		String id=request.getParameter("id");
		//�ж��ļ���Ϊ��
		if (!attach.isEmpty()) {
			//���ڱ��������ݿ��е�����
			String logoPicPath=null;
			String logoLocPath=null;
			//��ȡ�ļ���
			String fileName=attach.getOriginalFilename();
			//�����ļ��ϴ���·��
			logoPicPath="/appinfo/WebContent/statics/uploadfiles/"+fileName;
			logoLocPath=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles/"+fileName);
			String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			System.out.println();
			System.out.println(logoLocPath+"\n"+logoPicPath);
			System.out.println(request.getSession().getServletContext().getRealPath("uploadfiles"));
						//��ȡ��׺
			String prefix=FilenameUtils.getExtension(fileName);
			System.out.println(prefix+"--��׺");
			
			int fileSize=900000000;
			if (attach.getSize()>fileSize) {//�ļ�̫��
				request.setAttribute("uploadFileError","�ļ���С���ó���900000KB");
				return "redirect:/appinfo/appinfomodify?id="+id;
			}else if (prefix.equalsIgnoreCase("jpg")|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")|| prefix.equals("pneg")) {
				File file=new File(path,fileName);
				//�ж��ļ�·���Ƿ���ڣ������ھʹ���
				if (!file.exists()) {
					file.mkdirs();
				}
				//����
				try {
					attach.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("uploadFileError","�ϴ�ʧ��");
					return "redirect:/appinfo/appinfomodify?id="+id;
				}
				appInfo.setLogoPicPath(logoPicPath);
				appInfo.setLogoLocPath(logoLocPath);
			}else{
				request.setAttribute("uploadFileError","�ϴ����ļ���ʽ����ȷ");
				return "redirect:/appinfo/appinfomodify?id="+id;
			}
		}else {
			appInfo.setLogoPicPath("");
			appInfo.setLogoLocPath("");
		}
		//�޸ĵĲ���
		
		appInfo.setModifyBy(((DevUser)session.getAttribute("devUser")).getId());
		
		appInfo.setDevId(((DevUser)session.getAttribute("devUser")).getId());
		appInfo.setModifyDate(new Date());
		appInfo.setUpdateDate(new Date());
		int i=appinfoService.modifyAppInfo(appInfo);
		if (i>0) {
			return "redirect:/appinfo/list";
		}else{
			request.setAttribute("e","�޸�ʧ��");
			return "redirect:/appinfo/appinfomodify?id="+id;
		}
	}
	/**
	 * ɾ��ͼƬ��
	 */
	@RequestMapping("/delfiletup.json")
	@ResponseBody
	public Object delfiletup(HttpServletRequest request){
		//��ȡid
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
			File file=new File(appInfo.getLogoLocPath());//ɾ���ļ�
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
	 * �鿴
	 */
	@RequestMapping("/appview/{id}")
	public String appview(@PathVariable String id,HttpServletRequest request){
		//��ѯ����
		AppInfo appInfo=appinfoService.getAppInfoById(Integer.parseInt(id));
		request.setAttribute("appInfo", appInfo);
		/**
		 * ����id��ѯ�汾
		 */
		List<AppVersion> appVersionList=appVersionService.getAppVersionList(Integer.parseInt(id));
		System.out.println(appVersionList.size());
		request.setAttribute("appVersionList", appVersionList);
		return "developer/appinfoview";
	}
	/**
	 * ɾ��app
	 */
	@RequestMapping("delapp.json")
	@ResponseBody
	public String delapp(HttpServletRequest request){
		System.out.println("............");
		//��ȡid
		String id=request.getParameter("id");
		Map<String, String> map=new HashMap<String,String>();
		//��ɾ���汾
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
	 * �ж�apkName�Ƿ����
	 */
	@RequestMapping("/apkexist.json")
	@ResponseBody
	public Object apkexist(HttpServletRequest request){
		//��ȡ����
		String akpName=request.getParameter("APKName");
		Map<String, String> map=new HashMap<String,String>();
		if (akpName==null) {
			map.put("APKName", "empty");
		}
		
		//���÷���
		AppInfo appInfo=appinfoService.getAppInfoByapkName(akpName);
		if (appInfo!=null) {
			map.put("APKName", "exist");
		}else{
			map.put("APKName", "noexist");
		}
		return JSON.toJSONString(map);
	}
	
	/**
	 * �ϼܺ��¼�
	 */
	@RequestMapping("/sale.json")
	@ResponseBody
	public Object sale(HttpServletRequest request){
		//��ȡ��Ϣ
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
