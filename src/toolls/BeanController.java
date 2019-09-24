package toolls;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BeanController {
	/**
	 * 使用@initBinder解决SpringMVC的日期问题
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		
		System.out.println("=================");
		webDataBinder.registerCustomEditor(Date.class, 
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
