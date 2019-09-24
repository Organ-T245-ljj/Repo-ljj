package dao.developer.AppInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.AppInfo;

public interface AppInfoMapper {
	/**
	 * ��ѯ������
	 */
	public int getAppInfoCount(Map<String, Object> appMap);
	/**
	 * ��ҳ��ѯ
	 */
	public List<AppInfo> getAppInfoList(Map<String, Object> appMap);
	/**
	 * �������
	 */
	public int addApp(AppInfo appInfo);
	/**
	 * ����id��ѯ����
	 */
	public AppInfo getAppInfoById(@Param("id")int id);
	/**
	 * �������
	 */
	public int modifyAppInfo(AppInfo appInfo);
	/**
	 * �޸İ汾
	 */
	public int modifyVersionId(@Param("versionId")int versionId,@Param("id")int id);
	/**
	 * ɾ��app
	 */
	public int delApp(@Param("id")int id);
	/**
	 * ����AKPName��ѯ�Ƿ�������
	 */
	public AppInfo getAppInfoByapkName(@Param("APKName")String akpName);
	/**
	 *�޸�״̬
	 */
	public int modifystatus(@Param("status")int status,@Param("id")int id);
	
}
