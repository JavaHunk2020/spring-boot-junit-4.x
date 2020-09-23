package com.cubicit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JSONController {
	
	@GetMapping("/dog")
	public Dog getDog(){
		return new Dog();
	}

}
