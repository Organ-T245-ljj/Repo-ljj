package service.DataDictionary;

import java.util.List;

import pojo.DataDictionary;

public interface DataDictionaryService {
	/**
	 * ��ȡ����app��״̬
	 */
	public List<DataDictionary> getstatusList();
	/**
	 * ��ѯ���е�����ƽ̨
	 * @return
	 */
	public List<DataDictionary> getflatformList(String typeCode);
}
