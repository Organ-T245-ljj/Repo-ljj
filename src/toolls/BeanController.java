package toolls;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BeanController {
	/**
	 * ʹ��@initBinder���SpringMVC����������
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		
		System.out.println("=================");
		webDataBinder.registerCustomEditor(Date.class, 
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
