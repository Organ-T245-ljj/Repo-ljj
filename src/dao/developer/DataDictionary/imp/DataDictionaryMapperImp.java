package dao.developer.DataDictionary.imp;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.developer.DataDictionary.DataDictionaryMapper;
import pojo.DataDictionary;

public class DataDictionaryMapperImp extends SqlSessionDaoSupport implements DataDictionaryMapper{

	/**
	 * 获取所有的app状态
	 */
	public List<DataDictionary> getstatusList() {
		
		return super.getSqlSession().getMapper(DataDictionaryMapper.class).getstatusList();
	}

	@Override
	public List<DataDictionary> getflatformList(String typeCode) {
		
		return super.getSqlSession().getMapper(DataDictionaryMapper.class).getflatformList(typeCode);
	}
		
}
