package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SpringMVCInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		
		arg0.setCharacterEncoding("UTF-8");
		
		arg1.setCharacterEncoding("UTF-8");
		
		HttpSession session = arg0.getSession();

		String username = (String) session.getAttribute("custname");     
 
		String url = arg0.getRequestURI();

		System.out.println("Interceptor"+" "+url+" "+username);
		
		if (url.endsWith("login.do")||url.endsWith("checkname.do")||url.endsWith("register.do")) {

			return true;
			
		}
		
		if (username != null) {
		
			return true;
		}

		//arg0.getRequestDispatcher("/login.html").forward(arg0, arg1);
		
		arg1.sendRedirect("/login.html");

		return false;
	}
}
