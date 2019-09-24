package dao.developer.AppCategory;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.AppCategory;
import pojo.AppInfo;

public interface AppCategoryMapper {
	/**
	 * 查询一级分类
	 */
	public List<AppCategory> getAppCategoryByparentIdNUll();
	/**
	 * 根据父类id查询分类
	 */
	public List<AppCategory> getAppCategoryByparentId(@Param("parentId")int parentId);
	
}
