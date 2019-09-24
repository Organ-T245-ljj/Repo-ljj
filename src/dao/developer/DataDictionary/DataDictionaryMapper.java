package dao.developer.DataDictionary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.DataDictionary;

public interface DataDictionaryMapper {
	/**
	 * 获取所有app的状态getflatformList
	 */
	public List<DataDictionary> getstatusList();
	/**
	 * 查询所有的所属平台
	 * @return
	 */
	public List<DataDictionary> getflatformList(@Param("typeCode")String typeCode);
}
