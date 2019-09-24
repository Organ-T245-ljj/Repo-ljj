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
	 * �����汾������
	 */
	@RequestMapping("/appversionadd")
	public String appversionadd(HttpServletRequest request) {
		// ��ȡid
		String id = request.getParameter("id");
		System.out.println(id);
		request.setAttribute("appId", id);
		List<AppVersion> appVersions = appVersionService.getAppVersionList(Integer.parseInt(id));
		request.setAttribute("appVersionList", appVersions);
		return "developer/appversionadd";
	}

	/**
	 * �������
	 */
	@RequestMapping("/addversionsave")
	public String addversionsave(AppVersion appVersion, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "a_downloadLink", required = false) MultipartFile a_downloadLink) {
		// ���ڴ�������ݿ����Ϣ
		String downloadLink = null;
		String apkLocPath = null;
		String apkFileName = null;
		String id = request.getParameter("appId");
		// �ж��ļ��Ƿ����
		if (!a_downloadLink.isEmpty()) {
			// ��ȡ�ļ���
			apkFileName = a_downloadLink.getOriginalFilename();
			System.out.println(apkFileName);
			// ���ô�ŵ�ַ
			String Path =request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			apkLocPath =request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles/"+apkFileName);
			downloadLink = apkLocPath;
			// ��ȡ��׺
			String prefix = FilenameUtils.getExtension(apkFileName);
			System.out.println(prefix);
			int fileSize = 900000000;
			if (a_downloadLink.getSize() > fileSize) {// �ļ�̫��
				request.setAttribute("fileUploadError", "�ļ���С���ó���900000KB");
				System.out.println("�ļ���С���ó���900000KB");
				return "redirect:/appVersion/appversionadd?id=" + appVersion.getId();
			} else if (prefix.equals("apk")) {
				File file = new File(Path, apkFileName);
				// �ж��ļ�·���Ƿ���ڣ������ھʹ���
				if (!file.exists()) {
					file.mkdirs();
				}
				// ����
				try {
					a_downloadLink.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("fileUploadError", "�ϴ�ʧ��");
					System.out.println("�ϴ�ʧ��");
					return "redirect: ;/appVersion/appversionadd?id=" + id;
				}
			} else {
				request.setAttribute("fileUploadError", "�ļ���ʽ����");
				System.out.println("�ļ���ʽ����");
				return "redirect:/appVersion/appversionadd?id=" + id;
			}
		}
		// ��ӵ����ݿ�Ĳ���
		appVersion.setCreatedBy(((DevUser) session.getAttribute("devUser")).getId());
		appVersion.setCreationDate(new Date());
		appVersion.setDownloadLink(downloadLink);
		appVersion.setApkLocPath(apkLocPath);
		appVersion.setApkFileName(apkFileName);
		// ���÷���
		int i = appVersionService.addAppVersion(appVersion);
		if (i > 0) {
			AppVersion appVersion2=appVersionService.getAppInfoBycreationDateDESC(Integer.parseInt(id));
			int num=appinfoService.modifyVersionId(appVersion2.getId(), Integer.parseInt(id));
			//������״̬��Ϊ�����
			appinfoService.modifystatus(1, Integer.parseInt(id));
			System.out.println(num);
			return "redirect:/appinfo/list";
		} else {
			System.out.println("...........");
			return "redirect: /appVersion/appversionadd?id=" + id;
		}
	}

	/**
	 * �ļ�����
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(@RequestParam(value = "filename") String filename, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// ģ���ļ���myfile.txtΪ��Ҫ���ص��ļ�
		// String path =
		// "E:/y2/appinfo/WebContent/statics"+File.separator+"uploadfiles/"+filename;
		String path = "E:/y2/appinfo/WebContent/statics/uploadfiles/" + filename;
		System.out.println(path);
		// ��ȡ������
		InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
		// ת�룬����ļ�����������
		filename = URLEncoder.encode(filename, "UTF-8");
		// �����ļ�����ͷ
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		// 1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����
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
	 * �޸İ汾��Ϣ������
	 */
	@RequestMapping("/appversionmodify")
	public String appversionmodify(HttpServletRequest request) {
		// ��ȡ��Ϣ
		String vid = request.getParameter("vid");
		String aid = request.getParameter("aid");
		List<AppVersion> appVersions = appVersionService.getAppVersionList(Integer.parseInt(aid));
		request.setAttribute("appVersionList", appVersions);
		AppVersion appVersion = appVersionService.getAppVersionById(Integer.parseInt(vid));
		request.setAttribute("appVersion", appVersion);
		return "developer/appversionmodify";
	}

	/**
	 * �޸İ汾��Ϣ
	 */
	@RequestMapping("/appversionmodifysave")
	public String appversionmodifysave(AppVersion appVersion, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "attach", required = false) MultipartFile attach) {
		System.out.println(appVersion.getVersionInfo());
		// ���ڴ�������ݿ����Ϣ
		int falg=0;
		String id = request.getParameter("id");
		String aid=request.getParameter("appId");
		// �ж��ļ��Ƿ����
		if (!attach.isEmpty()) {
			String downloadLink = null;
			String apkLocPath = null;
			String apkFileName = null;
			// ��ȡ�ļ���
			apkFileName = attach.getOriginalFilename();
			System.out.println(apkFileName);
			// ���ô�ŵ�ַ
			String Path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			System.out.println(Path);
			apkLocPath=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles/"+apkFileName);
			downloadLink = apkLocPath;
			// ��ȡ��׺
			String prefix = FilenameUtils.getExtension(apkFileName);
			System.out.println(prefix);
			int fileSize = 900000000;
			if (attach.getSize() > fileSize) {// �ļ�̫��
				request.setAttribute("fileUploadError", "�ļ���С���ó���900000KB");
				System.out.println("�ļ���С���ó���900000KB");
				return "redirect:/appVersion/appversionmodify?id=" +id+"&aid="+aid;
			} else if (prefix.equals("apk")) {
				File file = new File(Path, apkFileName);
				// �ж��ļ�·���Ƿ���ڣ������ھʹ���
				if (!file.exists()) {
					file.mkdirs();
				}
				// ����
				try {
					attach.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("fileUploadError", "�ϴ�ʧ��");
					System.out.println("�ϴ�ʧ��");
					return "redirect:/appVersion/appversionmodify?vid=" + id+"+aid="+aid;
				}
				falg=1;
			} else {
				request.setAttribute("fileUploadError", "�ļ���ʽ����");
				System.out.println("�ļ���ʽ����");
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
	 * ɾ���ļ�
	 */
	@RequestMapping("/delfile.json")
	@ResponseBody
	public Object delfile(HttpServletRequest request){
		//��ȡid
		String id=request.getParameter("id");
		//��ȡ����
		AppVersion appVersion=new AppVersion();
		appVersion.setApkFileName(null);
		appVersion.setApkLocPath(null);
		appVersion.setDownloadLink(null);
		appVersion.setId(Integer.parseInt(id));
		AppVersion appVersion2=appVersionService.getAppVersionById(Integer.parseInt(id));
		
		//�����޸ĵķ���ֻ�޸Ĳ�������
		int i=appVersionService.modifyappversion(appVersion);
		System.out.println(appVersion2.getApkLocPath()+"------------------------");
		Map<String, String> map=new HashMap<String, String>();
		if (i>0) {
			File file=new File(appVersion2.getApkLocPath());//ɾ���ļ�
			
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
