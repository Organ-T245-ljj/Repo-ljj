package controller.developre;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.DevUser;
import service.DevUser.DevUserService;

@Controller
@RequestMapping("/dev")
public class DevUserController {
	@Autowired
	private DevUserService devUserService;
	/**
	 * 开发者平台 入口
	 */
	@RequestMapping("/login")
	public String login(){
		return "devlogin";
	}
	/**
	 * 登入
	 */
	@RequestMapping("dologin")
	public String dologin(HttpServletRequest request,HttpSession session){
		//获取信息
		String code=request.getParameter("devCode");
		String pwd=request.getParameter("devPassword");
		DevUser devUser=devUserService.getDevUserByCode(code);
		if (devUser==null) {
			//request.setAttribute("error", "用户名或密码错误");
			throw new RuntimeException("用户名不存在");
		}else{
			if (!pwd.equals(devUser.getDevPassword())){
				throw new RuntimeException("密码不正确");
			}else{
				session.setAttribute("devUser", devUser);
				return "developer/main";
			}
		}
	}
	/**
	 * 捕获异常
	 */
	@ExceptionHandler(value={RuntimeException.class})
	public String handlerExeption(RuntimeException e,HttpServletRequest request){
		request.setAttribute("error", e);
		return "devlogin";
	}
	/**
	 * 注销
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session,HttpServletResponse response){
		session.removeAttribute("devUser");
		return "devlogin";
	}
}
