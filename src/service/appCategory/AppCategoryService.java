package service.appCategory;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.AppCategory;

public interface AppCategoryService {
	/**
	 * ��ѯһ������
	 */
	public List<AppCategory> getAppCategoryByparentIdNUll();
	/**
	 * ���ݸ���id��ѯ����
	 */
	public List<AppCategory> getAppCategoryByparentId(int parentId);
}
