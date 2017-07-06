package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.Customer;
import com.po.UserState;
import com.service.CustomerService;

@Controller
public class CustomerController {

	@Resource
	CustomerService cs;

	Customer customer;

	@RequestMapping("/login.do")
	public void login(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String name = request.getParameter("custname");

		String pwd = request.getParameter("pwd");
		
		String rcode = request.getParameter("codename");
		
		String pcode = request.getParameter("code");
		
		Customer customer = new Customer();

		customer.setCustname(name);

		customer.setPwd(pwd);

		HttpSession session = request.getSession();

		if(name == null)
		{
			response.sendRedirect( "login.html");
			return;
		}
		if(!cs.checkUserName(name))
		{
			response.sendRedirect( "Sorry.html");
			return;
		}
		if (cs.check(customer)) {
			
			session.setAttribute("pwd", pwd);

			session.setAttribute("custname", name);
			
			if(rcode.equals(pcode))
			{
				response.sendRedirect("resource/welcome.html");
				return;
				
			}
		}
		response.sendRedirect("login.html");
	}

	@RequestMapping("/checkname.do")
	public void checkname(Model model, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String name = request.getParameter("custname");

		boolean cun = cs.checkUserName(name);

		String exist;

		if (cun) {

			exist = "You can't use this name!";

		} else {

			exist = "You can use this name!";

		}

		PrintWriter out = null;

		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println(exist);

		out.close();

	}

	@RequestMapping("/register.do")
	public void register(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		String name = request.getParameter("custname");

		String pwd = request.getParameter("pwd");

		String gender = request.getParameter("sex");

		String phone = request.getParameter("pname");

		boolean cun = cs.checkUserName(name);

		Customer customer = new Customer();

		customer.setCustname(name);

		customer.setPwd(pwd);

		customer.setSex(gender);
		
		customer.setPname(phone);

		if (name == null || pwd == null || gender == null || phone == null) {
			response.sendRedirect("resource/rgister.html"); 
			//
			return;
		}

		if (cun) {
			response.sendRedirect("resource/rgister.html");
			return;
		}

		session.setAttribute("custname", name);
		session.setAttribute("pwd", pwd);
		cs.addUser(customer);
		response.sendRedirect("resource/welcome.html"); 

	}

	@RequestMapping("/showInfo.do")
	public String showInfo(Model model, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();

		String name = (String) session.getAttribute("custname");

		customer = cs.getCustomer(name);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println(customer.toString());

		out.close();

		return "resource/welcome";
	}

	@RequestMapping("/change.do")
	public void change(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String oldpwd = request.getParameter("oldpwd");

		String newpwd = request.getParameter("newpwd");
		
		String rpwd = (String) request.getSession().getAttribute("pwd");
		
		System.out.println("change.do"+rpwd);

		if (!(oldpwd.equals(rpwd))) {
			
			response.sendRedirect("resource/change.html");

			return ;

		} else {
			customer = new Customer();
			
			customer.setPwd(newpwd);
			
			customer.setCustname((String) request.getSession().getAttribute("custname"));

			cs.updatePwd(customer);

			response.sendRedirect("resource/welcome.html");
		}

	}

	@RequestMapping("/delete.do")
	public void delete(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		String name = (String) session.getAttribute("custname");

		System.out.println("hello   " + name);

		cs.delete(name);

		session.removeAttribute("custname");

		response.sendRedirect("login.html"); 

	}
	@RequestMapping("/addState.do")
	public String addState(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();

		String name = (String) session.getAttribute("custname");
		
		List<UserState> usList = cs.getStates();
		
		for(UserState us:usList){
			if(us.getName().equals(name))
			{
				//response.sendRedirect("exists.html");
				System.out.println(us.getName());
				return "exists";
			}
		}
		UserState userState = new UserState();
		
		userState.setName(name);
		
		cs.addState(userState);
		//response.sendRedirect("success.html");
		return "success";

	}

}
