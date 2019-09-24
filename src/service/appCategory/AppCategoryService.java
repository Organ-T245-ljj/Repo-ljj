package service.appCategory;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.AppCategory;

public interface AppCategoryService {
	/**
	 * 查询一级分类
	 */
	public List<AppCategory> getAppCategoryByparentIdNUll();
	/**
	 * 根据父类id查询分类
	 */
	public List<AppCategory> getAppCategoryByparentId(int parentId);
}
