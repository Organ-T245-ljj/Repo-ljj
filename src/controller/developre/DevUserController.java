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
	 * ������ƽ̨ ���
	 */
	@RequestMapping("/login")
	public String login(){
		return "devlogin";
	}
	/**
	 * ����
	 */
	@RequestMapping("dologin")
	public String dologin(HttpServletRequest request,HttpSession session){
		//��ȡ��Ϣ
		String code=request.getParameter("devCode");
		String pwd=request.getParameter("devPassword");
		DevUser devUser=devUserService.getDevUserByCode(code);
		if (devUser==null) {
			//request.setAttribute("error", "�û������������");
			throw new RuntimeException("�û���������");
		}else{
			if (!pwd.equals(devUser.getDevPassword())){
				throw new RuntimeException("���벻��ȷ");
			}else{
				session.setAttribute("devUser", devUser);
				return "developer/main";
			}
		}
	}
	/**
	 * �����쳣
	 */
	@ExceptionHandler(value={RuntimeException.class})
	public String handlerExeption(RuntimeException e,HttpServletRequest request){
		request.setAttribute("error", e);
		return "devlogin";
	}
	/**
	 * ע��
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session,HttpServletResponse response){
		session.removeAttribute("devUser");
		return "devlogin";
	}
}
