package service.DataDictionary.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.developer.DataDictionary.DataDictionaryMapper;
import pojo.DataDictionary;
import service.DataDictionary.DataDictionaryService;
@Service("dataDictionaryService")
public class DataDictionaryServiceImp implements DataDictionaryService {
	
	@Autowired
	private DataDictionaryMapper dataDictionaryMapper;
	/**
	 * 获取所有的app状态
	 */
	public List<DataDictionary> getstatusList() {
		
		return dataDictionaryMapper.getstatusList();
	}
	/**
	 * 查询所有的所属平台
	 */
	public List<DataDictionary> getflatformList(String typeCode) {
		return dataDictionaryMapper.getflatformList(typeCode);
	}

}
