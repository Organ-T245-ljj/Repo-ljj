package dao.developer.DataDictionary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.DataDictionary;

public interface DataDictionaryMapper {
	/**
	 * ��ȡ����app��״̬getflatformList
	 */
	public List<DataDictionary> getstatusList();
	/**
	 * ��ѯ���е�����ƽ̨
	 * @return
	 */
	public List<DataDictionary> getflatformList(@Param("typeCode")String typeCode);
}
