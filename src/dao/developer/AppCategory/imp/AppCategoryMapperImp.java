package dao.developer.AppCategory.imp;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.developer.AppCategory.AppCategoryMapper;
import pojo.AppCategory;
import pojo.AppInfo;

public class AppCategoryMapperImp extends SqlSessionDaoSupport implements AppCategoryMapper{

	/**
	 * ��ѯһ������
	 */
	public List<AppCategory> getAppCategoryByparentIdNUll() {
		
		return super.getSqlSession().getMapper(AppCategoryMapper.class).getAppCategoryByparentIdNUll();
	}

	/**
	 * ���ݷ���id��ѯ����
	 */
	public List<AppCategory> getAppCategoryByparentId(int parentId) {
		
		return super.getSqlSession().getMapper(AppCategoryMapper.class).getAppCategoryByparentId(parentId);
	}

}
