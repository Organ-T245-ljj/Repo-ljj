package service.appCategory.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.developer.AppCategory.AppCategoryMapper;
import pojo.AppCategory;
import service.appCategory.AppCategoryService;
import service.appinfo.AppinfoService;
@Service("appCategoryService")
public class AppCategoryServiceImp implements AppCategoryService {
	@Autowired
	private AppCategoryMapper appCategoryMapper;
	@Override
	public List<AppCategory> getAppCategoryByparentIdNUll() {
		
		return appCategoryMapper.getAppCategoryByparentIdNUll();
	}
	@Override
	public List<AppCategory> getAppCategoryByparentId(int parentId) {
		
		return appCategoryMapper.getAppCategoryByparentId(parentId);
	}
	
}
