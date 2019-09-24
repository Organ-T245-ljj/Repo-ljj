package dao.developer.AppVersion.imp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.developer.AppInfo.AppInfoMapper;
import dao.developer.AppVersion.AppVersionMapper;
import pojo.AppInfo;
import pojo.AppVersion;

public class AppVersionMapperImp extends SqlSessionDaoSupport implements AppVersionMapper {

	@Override
	public List<AppVersion> getAppVersionList(int appId) {
	
		return super.getSqlSession().getMapper(AppVersionMapper.class).getAppVersionList(appId);
	}

	@Override
	public int addAppVersion(AppVersion appVersion) {
		
		return super.getSqlSession().getMapper(AppVersionMapper.class).addAppVersion(appVersion);
	}

	@Override
	public AppVersion getAppVersionById(int id) {
		
		return super.getSqlSession().getMapper(AppVersionMapper.class).getAppVersionById(id);
	}

	@Override
	public int modifyappversion(AppVersion appVersion) {
		
		return super.getSqlSession().getMapper(AppVersionMapper.class).modifyappversion(appVersion);
	}
	@Override
	public AppVersion getAppInfoBycreationDateDESC(int appId) {
		
		return super.getSqlSession().getMapper(AppVersionMapper.class).getAppInfoBycreationDateDESC(appId);
	}

	@Override
	public int delAppVesionByAppId(int appId) {
		
		return super.getSqlSession().getMapper(AppVersionMapper.class).delAppVesionByAppId(appId);
	}
}
