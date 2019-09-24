package controller.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.AppCategory;
import pojo.AppInfo;
import pojo.AppVersion;
import pojo.BackendUser;
import pojo.DataDictionary;
import service.BackendUser.BackendUserService;
import service.DataDictionary.DataDictionaryService;
import service.appCategory.AppCategoryService;
import service.appVersion.AppVersionService;
import service.appinfo.AppinfoService;

@Controller
@RequestMapping("/backendUser")
public class BackendUserController {
	@Autowired
	private BackendUserService backendUserService;
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
	 * 登入的请求
	 */
	@RequestMapping("/login")
	public String login(){
		return "backendlogin";
	}
	/**
	 * 登入
	 */
	@RequestMapping("/dologin")
	public String dologin(HttpServletRequest request,HttpSession session){
		//获取信息
		String userCode=request.getParameter("userCode");
		String userPassword=request.getParameter("userPassword");
		//调用方法查询
		BackendUser backendUser=backendUserService.getBackendUserByNameANDPwd(userCode);
		if (backendUser==null) {
			throw new RuntimeException("用户名不存在");
		}else{
			if (!backendUser.getUserPassword().equals(userPassword)) {
				throw new RuntimeException("用户名不存在");
			}else{
				session.setAttribute("backendUser", backendUser);
				return "backend/main";
			}
		}
	}
	/**
	 * 捕获异常
	 */
	@ExceptionHandler(value={RuntimeException.class})
	public String handlerExeption(RuntimeException e,HttpServletRequest request){
		request.setAttribute("error", e);
		return "backendlogin";
	}
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		//获取信息
		String querySoftwareName=request.getParameter("querySoftwareName");
		String queryStatus=request.getParameter("queryStatus");
		String queryFlatformId=request.getParameter("queryFlatformId");
		String categoryLevel1List=request.getParameter("queryCategoryLevel1");
		System.out.println(categoryLevel1List+"0+................");
		String categoryLevel2List=request.getParameter("queryCategoryLevel2");
		System.out.println(categoryLevel2List);
		String categoryLevel3List=request.getParameter("queryCategoryLevel3");
		List<AppCategory> categoryLevel2Lists=null;
		List<AppCategory> categoryLevel3Lists=null;
		if (categoryLevel2List=="0" || categoryLevel2List !=null) {
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
		return "backend/applist";			
	}
	/**
	 * 审核的请求
	 */
	@RequestMapping("/check")
	public String check(HttpServletRequest request){
		String aid=request.getParameter("aid");
		String vid=request.getParameter("vid");
		//获取数据
		AppInfo appInfo=appinfoService.getAppInfoById(Integer.parseInt(aid));
		AppVersion appVersion=appVersionService.getAppInfoBycreationDateDESC(Integer.parseInt(aid));
		request.setAttribute("appInfo", appInfo);
		request.setAttribute("appVersion", appVersion);
		return "backend/appcheck";
	}
	/**
	 * 审核
	 */
	@RequestMapping("/checksave")
	public String checksave(HttpServletRequest request){
		//获取信息
		String status=request.getParameter("status");
		String id=request.getParameter("id");
		System.out.println(status+"--------------"+id);
		int i=appinfoService.modifystatus(Integer.parseInt(status),Integer.parseInt(id));
		System.out.println(i);
		if (i>0) {
			return "redirect:/backendUser/list";
		}else{
			return "redirect:/backendUser/check";
		}
		
	}
	/**
	 * 注销
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session,HttpServletResponse response){
		session.removeAttribute("devUser");
		return "backendlogin";
	}
}
