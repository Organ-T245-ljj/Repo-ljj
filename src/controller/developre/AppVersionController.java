package controller.developre;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import pojo.AppInfo;
import pojo.AppVersion;
import pojo.DevUser;
import service.appVersion.AppVersionService;
import service.appinfo.AppinfoService;

@Controller
@RequestMapping("/appVersion")
public class AppVersionController {
	@Autowired
	private AppVersionService appVersionService;
	@Autowired
	private AppinfoService appinfoService;
	/**
	 * 新增版本的请求
	 */
	@RequestMapping("/appversionadd")
	public String appversionadd(HttpServletRequest request) {
		// 获取id
		String id = request.getParameter("id");
		System.out.println(id);
		request.setAttribute("appId", id);
		List<AppVersion> appVersions = appVersionService.getAppVersionList(Integer.parseInt(id));
		request.setAttribute("appVersionList", appVersions);
		return "developer/appversionadd";
	}

	/**
	 * 添加数据
	 */
	@RequestMapping("/addversionsave")
	public String addversionsave(AppVersion appVersion, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "a_downloadLink", required = false) MultipartFile a_downloadLink) {
		// 用于存放在数据库的信息
		String downloadLink = null;
		String apkLocPath = null;
		String apkFileName = null;
		String id = request.getParameter("appId");
		// 判断文件是否存在
		if (!a_downloadLink.isEmpty()) {
			// 获取文件名
			apkFileName = a_downloadLink.getOriginalFilename();
			System.out.println(apkFileName);
			// 设置存放地址
			String Path =request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			apkLocPath =request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles/"+apkFileName);
			downloadLink = apkLocPath;
			// 获取后缀
			String prefix = FilenameUtils.getExtension(apkFileName);
			System.out.println(prefix);
			int fileSize = 900000000;
			if (a_downloadLink.getSize() > fileSize) {// 文件太大
				request.setAttribute("fileUploadError", "文件大小不得超过900000KB");
				System.out.println("文件大小不得超过900000KB");
				return "redirect:/appVersion/appversionadd?id=" + appVersion.getId();
			} else if (prefix.equals("apk")) {
				File file = new File(Path, apkFileName);
				// 判断文件路径是否存在，不存在就创建
				if (!file.exists()) {
					file.mkdirs();
				}
				// 保存
				try {
					a_downloadLink.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("fileUploadError", "上传失败");
					System.out.println("上传失败");
					return "redirect: ;/appVersion/appversionadd?id=" + id;
				}
			} else {
				request.setAttribute("fileUploadError", "文件格式不对");
				System.out.println("文件格式不对");
				return "redirect:/appVersion/appversionadd?id=" + id;
			}
		}
		// 添加到数据库的操作
		appVersion.setCreatedBy(((DevUser) session.getAttribute("devUser")).getId());
		appVersion.setCreationDate(new Date());
		appVersion.setDownloadLink(downloadLink);
		appVersion.setApkLocPath(apkLocPath);
		appVersion.setApkFileName(apkFileName);
		// 调用方法
		int i = appVersionService.addAppVersion(appVersion);
		if (i > 0) {
			AppVersion appVersion2=appVersionService.getAppInfoBycreationDateDESC(Integer.parseInt(id));
			int num=appinfoService.modifyVersionId(appVersion2.getId(), Integer.parseInt(id));
			//将它的状态变为带审核
			appinfoService.modifystatus(1, Integer.parseInt(id));
			System.out.println(num);
			return "redirect:/appinfo/list";
		} else {
			System.out.println("...........");
			return "redirect: /appVersion/appversionadd?id=" + id;
		}
	}

	/**
	 * 文件下载
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(@RequestParam(value = "filename") String filename, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 模拟文件，myfile.txt为需要下载的文件
		// String path =
		// "E:/y2/appinfo/WebContent/statics"+File.separator+"uploadfiles/"+filename;
		String path = "E:/y2/appinfo/WebContent/statics/uploadfiles/" + filename;
		System.out.println(path);
		// 获取输入流
		InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
		// 转码，免得文件名中文乱码
		filename = URLEncoder.encode(filename, "UTF-8");
		// 设置文件下载头
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = 0;
		while ((len = bis.read()) != -1) {
			out.write(len);
			out.flush();
		}
		out.close();
	}

	/**
	 * 修改版本信息的请求
	 */
	@RequestMapping("/appversionmodify")
	public String appversionmodify(HttpServletRequest request) {
		// 获取信息
		String vid = request.getParameter("vid");
		String aid = request.getParameter("aid");
		List<AppVersion> appVersions = appVersionService.getAppVersionList(Integer.parseInt(aid));
		request.setAttribute("appVersionList", appVersions);
		AppVersion appVersion = appVersionService.getAppVersionById(Integer.parseInt(vid));
		request.setAttribute("appVersion", appVersion);
		return "developer/appversionmodify";
	}

	/**
	 * 修改版本信息
	 */
	@RequestMapping("/appversionmodifysave")
	public String appversionmodifysave(AppVersion appVersion, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "attach", required = false) MultipartFile attach) {
		System.out.println(appVersion.getVersionInfo());
		// 用于存放在数据库的信息
		int falg=0;
		String id = request.getParameter("id");
		String aid=request.getParameter("appId");
		// 判断文件是否存在
		if (!attach.isEmpty()) {
			String downloadLink = null;
			String apkLocPath = null;
			String apkFileName = null;
			// 获取文件名
			apkFileName = attach.getOriginalFilename();
			System.out.println(apkFileName);
			// 设置存放地址
			String Path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			System.out.println(Path);
			apkLocPath=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles/"+apkFileName);
			downloadLink = apkLocPath;
			// 获取后缀
			String prefix = FilenameUtils.getExtension(apkFileName);
			System.out.println(prefix);
			int fileSize = 900000000;
			if (attach.getSize() > fileSize) {// 文件太大
				request.setAttribute("fileUploadError", "文件大小不得超过900000KB");
				System.out.println("文件大小不得超过900000KB");
				return "redirect:/appVersion/appversionmodify?id=" +id+"&aid="+aid;
			} else if (prefix.equals("apk")) {
				File file = new File(Path, apkFileName);
				// 判断文件路径是否存在，不存在就创建
				if (!file.exists()) {
					file.mkdirs();
				}
				// 保存
				try {
					attach.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("fileUploadError", "上传失败");
					System.out.println("上传失败");
					return "redirect:/appVersion/appversionmodify?vid=" + id+"+aid="+aid;
				}
				falg=1;
			} else {
				request.setAttribute("fileUploadError", "文件格式不对");
				System.out.println("文件格式不对");
				return "redirect:/appVersion/appversionmodify?id=" + id+"&aid"+aid;
			}
			appVersion.setDownloadLink(downloadLink);
			appVersion.setApkLocPath(apkLocPath);
			appVersion.setApkFileName(apkFileName);
		}else{
			appVersion.setDownloadLink("");
			appVersion.setApkLocPath("");
			appVersion.setApkFileName("");
		}
		System.out.println(falg);
		appVersion.setModifyBy(((DevUser) session.getAttribute("devUser")).getId());
		appVersion.setModifyDate(new Date());
		int i=appVersionService.modifyappversion(appVersion);
		if (i>0) {
			return "redirect:/appinfo/list";
		}else{
			return "redirect:/appVersion/appversionmodify?id=" + id+"&aid"+aid;
		}
		
	}
	/**
	 * 删除文件
	 */
	@RequestMapping("/delfile.json")
	@ResponseBody
	public Object delfile(HttpServletRequest request){
		//获取id
		String id=request.getParameter("id");
		//获取数据
		AppVersion appVersion=new AppVersion();
		appVersion.setApkFileName(null);
		appVersion.setApkLocPath(null);
		appVersion.setDownloadLink(null);
		appVersion.setId(Integer.parseInt(id));
		AppVersion appVersion2=appVersionService.getAppVersionById(Integer.parseInt(id));
		
		//调用修改的方法只修改部分数据
		int i=appVersionService.modifyappversion(appVersion);
		System.out.println(appVersion2.getApkLocPath()+"------------------------");
		Map<String, String> map=new HashMap<String, String>();
		if (i>0) {
			File file=new File(appVersion2.getApkLocPath());//删除文件
			
			if (file.delete()) {
				System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
			}
			map.put("result", "success");
		}else{
			map.put("result", "failed");
		}
		return JSON.toJSONString(map);
	}
	
}
