package com.cubicit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // this annotation ensures this is my controller
public class HelloController {
	
	@GetMapping({"/aha","/hello","cry"})
	public String execute(){
		return "hello"; // ->> /hello.jsp
	}

}
