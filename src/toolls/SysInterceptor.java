package toolls;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pojo.BackendUser;
import pojo.DevUser;

public class SysInterceptor extends HandlerInterceptorAdapter {
		private Logger logger=Logger.getLogger(getClass());
		public void afterCompletion(HttpServletRequest arg0,
				HttpServletResponse arg1, Object arg2, Exception arg3)
				throws Exception {
			System.out.println("���ִ�У�����һ�������ͷ���Դ����");
			
		}

		public void postHandle(HttpServletRequest arg0, HttpServletResponse response,
				Object arg2, ModelAndView arg3) throws Exception {
			System.out.println("Actionִ��֮��������ͼ֮ǰִ�У���");
		}

		public boolean preHandle(HttpServletRequest request,HttpServletResponse response ,Object handler) throws IOException{
			System.out.println("action֮ǰִ�У�����");
			String url = request.getRequestURL().toString();
			System.out.println("url->"+url);
			
			int index = url.lastIndexOf('/');
			
			System.out.println("����ķ����ǣ�"+url.substring(index+1, url.length()));
			
			String action = url.substring(index+1, url.length());
			BackendUser backendUser=(BackendUser)request.getSession().getAttribute("backendUser");
			DevUser str = (DevUser) request.getSession().getAttribute("devUser");  
	        System.out.println("str=========>"+str);  
			
			if(action.equals("dologin")||action.equals("login") ||str!=null || backendUser!=null){
				return true;  //����ִ��action,�����ص�½�ķ�����
			}
			response.sendRedirect(request.getContextPath()+"/403.jsp");
	        return false;
		}
}
