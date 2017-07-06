package com.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest request = (HttpServletRequest) arg0;

		HttpServletResponse response = (HttpServletResponse) arg1;
		
		arg0.setCharacterEncoding("UTF-8");
		arg1.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		
		String name = (String) session.getAttribute("custname");
		
		String url = request.getRequestURI();
		
		System.out.println("filter"+" "+url+" "+name);
		
		if(url.endsWith("login.html")||url.endsWith("rgister.html")||url.endsWith("Sorry.html"))
		{
			arg2.doFilter(arg0, arg1);
			return;
		}
		if(name == null)
		{
			response.sendRedirect("/Login/login.html");
		}

		arg2.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
