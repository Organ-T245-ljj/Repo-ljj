package service.DataDictionary;

import java.util.List;

import pojo.DataDictionary;

public interface DataDictionaryService {
	/**
	 * 获取所有app的状态
	 */
	public List<DataDictionary> getstatusList();
	/**
	 * 查询所有的所属平台
	 * @return
	 */
	public List<DataDictionary> getflatformList(String typeCode);
}
