package dao.developer.AppInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.AppInfo;

public interface AppInfoMapper {
	/**
	 * 查询总数量
	 */
	public int getAppInfoCount(Map<String, Object> appMap);
	/**
	 * 分页查询
	 */
	public List<AppInfo> getAppInfoList(Map<String, Object> appMap);
	/**
	 * 添加数据
	 */
	public int addApp(AppInfo appInfo);
	/**
	 * 根据id查询数据
	 */
	public AppInfo getAppInfoById(@Param("id")int id);
	/**
	 * 添加数据
	 */
	public int modifyAppInfo(AppInfo appInfo);
	/**
	 * 修改版本
	 */
	public int modifyVersionId(@Param("versionId")int versionId,@Param("id")int id);
	/**
	 * 删除app
	 */
	public int delApp(@Param("id")int id);
	/**
	 * 根据AKPName查询是否有数据
	 */
	public AppInfo getAppInfoByapkName(@Param("APKName")String akpName);
	/**
	 *修改状态
	 */
	public int modifystatus(@Param("status")int status,@Param("id")int id);
	
}
