package dao.developer.AppCategory;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.AppCategory;
import pojo.AppInfo;

public interface AppCategoryMapper {
	/**
	 * ��ѯһ������
	 */
	public List<AppCategory> getAppCategoryByparentIdNUll();
	/**
	 * ���ݸ���id��ѯ����
	 */
	public List<AppCategory> getAppCategoryByparentId(@Param("parentId")int parentId);
	
}
