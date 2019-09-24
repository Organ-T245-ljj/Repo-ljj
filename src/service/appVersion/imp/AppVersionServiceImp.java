package service.appVersion.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.developer.AppVersion.AppVersionMapper;
import pojo.AppInfo;
import pojo.AppVersion;
import service.appVersion.AppVersionService;

@Transactional
@Service("appVersionService")
public class AppVersionServiceImp implements AppVersionService {
	@Autowired
	private AppVersionMapper appVersionMapper;
	public List<AppVersion> getAppVersionList(int appId) {
		
		return appVersionMapper.getAppVersionList(appId);
	}
	@Override
	public int addAppVersion(AppVersion appVersion) {
		return appVersionMapper.addAppVersion(appVersion);
	}
	@Override
	public AppVersion getAppVersionById(int id) {
		
		return appVersionMapper.getAppVersionById(id);
	}
	@Override
	public int modifyappversion(AppVersion appVersion) {
		
		return appVersionMapper.modifyappversion(appVersion);
	}
	@Override
	public AppVersion getAppInfoBycreationDateDESC(int appId) {
		
		return appVersionMapper.getAppInfoBycreationDateDESC(appId);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int delAppVesionByAppId(int appId) {
		
		return appVersionMapper.delAppVesionByAppId(appId);
	}
}
