package service.appinfo.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.developer.AppInfo.AppInfoMapper;
import pojo.AppInfo;
import service.appinfo.AppinfoService;

@Service("appinfoService")
@Transactional
public class AppinfoServiceImp implements AppinfoService {
	@Autowired
	private AppInfoMapper appInfoMapper;
	/**
	 * 获取总数量
	 */
	public int getAppInfoCount(Map<String, Object> appMap) {
		
		return appInfoMapper.getAppInfoCount(appMap);
	}

	@Override
	public List<AppInfo> getAppInfoList(Map<String, Object> appMap) {
		return appInfoMapper.getAppInfoList(appMap);
	}

	@Override
	public int addApp(AppInfo appInfo) {
	
		return appInfoMapper.addApp(appInfo);
	}

	@Override
	public AppInfo getAppInfoById(int id) {
		
		return appInfoMapper.getAppInfoById(id);
	}

	@Override
	public int modifyAppInfo(AppInfo appInfo) {
		
		return appInfoMapper.modifyAppInfo(appInfo);
	}

	@Override
	public int modifyVersionId(int versionId, int id) {
		
		return appInfoMapper.modifyVersionId(versionId, id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int delApp(int id) {
		
		return appInfoMapper.delApp(id);
	}

	@Override
	public AppInfo getAppInfoByapkName(String akpName) {
		
		return appInfoMapper.getAppInfoByapkName(akpName);
	}

	@Override
	public int modifystatus(int status,int id) {
		
		return appInfoMapper.modifystatus(status,id);
	}

}
