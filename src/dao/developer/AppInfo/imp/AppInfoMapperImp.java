package dao.developer.AppInfo.imp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.developer.AppInfo.AppInfoMapper;
import dao.developer.AppVersion.AppVersionMapper;
import pojo.AppInfo;

public class AppInfoMapperImp extends SqlSessionDaoSupport implements AppInfoMapper {

	@Override
	public int getAppInfoCount(Map<String, Object> appMap) {
		
		return super.getSqlSession().getMapper(AppInfoMapper.class).getAppInfoCount(appMap);
	}

	@Override
	public List<AppInfo> getAppInfoList(Map<String, Object> appMap) {
		
		return super.getSqlSession().getMapper(AppInfoMapper.class).getAppInfoList(appMap);
	}

	@Override
	public int addApp(AppInfo appInfo) {
		
		return super.getSqlSession().getMapper(AppInfoMapper.class).addApp(appInfo);
	}

	@Override
	public AppInfo getAppInfoById(int id) {
		
		return super.getSqlSession().getMapper(AppInfoMapper.class).getAppInfoById(id);
	}

	@Override
	public int modifyAppInfo(AppInfo appInfo) {
		
		return super.getSqlSession().getMapper(AppInfoMapper.class).modifyAppInfo(appInfo);
	}

	@Override
	public int modifyVersionId(int versionId, int id) {
		
		return super.getSqlSession().getMapper(AppInfoMapper.class).modifyVersionId(versionId, id);
	}

	@Override
	public int delApp(int id) {
		
		return super.getSqlSession().getMapper(AppInfoMapper.class).delApp(id);
	}

	@Override
	public AppInfo getAppInfoByapkName(String akpName) {
		
		return super.getSqlSession().getMapper(AppInfoMapper.class).getAppInfoByapkName(akpName);
	}

	@Override
	public int modifystatus(int status,int id) {
		
		return super.getSqlSession().getMapper(AppInfoMapper.class).modifystatus(status,id);
	}

	
	
}
