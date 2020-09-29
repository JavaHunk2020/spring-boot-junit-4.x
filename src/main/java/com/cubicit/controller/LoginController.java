package com.cubicit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cubicit.controller.vo.CustomerVO;
import com.cubicit.service.CustomerServiceImpl;

@Controller
public class LoginController {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	//EL - EL Implicit Object -13 
    //${sessionScope.customer.cid}
	@GetMapping("/customer/dashboard")
	public String ghome(HttpSession session){
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String username=authentication.getName();// javatech1000@gmail.com
		  CustomerVO customerVO=customerServiceImpl.findByEmail(username);
		  session.setAttribute("customer", customerVO);
		  return "home";
	}
	
	//This will execute when u do not have access desire role
	@GetMapping("/access/denied")
	public String accessDenied(){
		return "accessDenied";
	}
	
	
//  <form action="authUser" method="POST">
	@GetMapping({"/oauth","/"})
	public String showPage(){
		return "auth"; // ->> /auth.jsp
	}

}
