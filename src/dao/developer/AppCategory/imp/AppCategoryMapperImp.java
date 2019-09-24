package dao.developer.AppCategory.imp;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.developer.AppCategory.AppCategoryMapper;
import pojo.AppCategory;
import pojo.AppInfo;

public class AppCategoryMapperImp extends SqlSessionDaoSupport implements AppCategoryMapper{

	/**
	 * 查询一级分类
	 */
	public List<AppCategory> getAppCategoryByparentIdNUll() {
		
		return super.getSqlSession().getMapper(AppCategoryMapper.class).getAppCategoryByparentIdNUll();
	}

	/**
	 * 根据夫类id查询分类
	 */
	public List<AppCategory> getAppCategoryByparentId(int parentId) {
		
		return super.getSqlSession().getMapper(AppCategoryMapper.class).getAppCategoryByparentId(parentId);
	}

}
