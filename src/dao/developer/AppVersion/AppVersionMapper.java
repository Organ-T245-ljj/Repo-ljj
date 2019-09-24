package dao.developer.AppVersion;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.AppInfo;
import pojo.AppVersion;

public interface AppVersionMapper {

	 public List<AppVersion> getAppVersionList(@Param("appId")int appId);
	 public int addAppVersion(AppVersion appVersion);
	 public AppVersion getAppVersionById(@Param("id")int id);
	 public int modifyappversion(AppVersion appVersion);
	 /**
		 * ��ѯ�뵱ǰϵͳʱ�����������
		 */
		public AppVersion getAppInfoBycreationDateDESC(@Param("appId")int appId); 
	/**
	 * ����appIdɾ���汾	
	 */
	public int delAppVesionByAppId(@Param("appId")int appId);	
}
