package controller.developre;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pojo.DataDictionary;
import service.DataDictionary.DataDictionaryService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/dataDic")
public class DataDictionaryController {
	@Autowired
	private DataDictionaryService dataDictionaryService;
	/**
	 * 获取所有的app所属平台
	 */
	@RequestMapping("/datadictionarylist.json")
	@ResponseBody
	public Object datadictionarylist(HttpServletRequest request){
		String code=request.getParameter("tcode");
		List<DataDictionary> dataDictionaries=dataDictionaryService.getflatformList(code);
		System.out.println(JSON.toJSONString(dataDictionaries));
		return JSON.toJSONString(dataDictionaries);
	}
}
