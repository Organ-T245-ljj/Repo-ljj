package controller.developre;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pojo.AppCategory;
import service.appCategory.AppCategoryService;


@Controller
@RequestMapping("/appCategory")
public class AppCategoryController {
	@Autowired
	private AppCategoryService appCategoryService;
	/**
	 * 加载分类
	 */
	@RequestMapping("/categorylevellist")
	@ResponseBody
	public Object categorylevellist(HttpServletRequest request){
		//获取信息
		String pid=request.getParameter("pid");
		System.out.println(pid);
		//调用方法
		List<AppCategory> appCategories=appCategoryService.getAppCategoryByparentId(Integer.parseInt(pid));
		System.out.println(JSON.toJSONString(appCategories));
		return JSON.toJSONString(appCategories);
	}
	/**
	 * categorylevellist ajax加载一级分类
	 */
	@RequestMapping("/categorylevellist.json")
	@ResponseBody
	public Object categorylevellist(){
		List<AppCategory> appCategories=appCategoryService.getAppCategoryByparentIdNUll();
		return JSON.toJSONString(appCategories);
	}
}
