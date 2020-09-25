package com.cubicit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	

	@GetMapping("/customer/dashboard")
	public String ghome(){
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
