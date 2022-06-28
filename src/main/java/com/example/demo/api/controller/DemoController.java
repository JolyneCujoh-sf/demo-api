package com.example.demo.api.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo")
@RestController
public class DemoController {

	@GetMapping("/hello")
	public HashMap sayHello() {
		HashMap map = new HashMap<>() {{
			put("result", "HelloWorld");
		}};
		return map;

	}
}
