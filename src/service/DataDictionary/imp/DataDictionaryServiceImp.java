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
	 * ��ȡ���е�app״̬
	 */
	public List<DataDictionary> getstatusList() {
		
		return dataDictionaryMapper.getstatusList();
	}
	/**
	 * ��ѯ���е�����ƽ̨
	 */
	public List<DataDictionary> getflatformList(String typeCode) {
		return dataDictionaryMapper.getflatformList(typeCode);
	}

}
