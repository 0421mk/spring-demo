package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕하세요.";
	}
	
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2() {
		return "안녕하세요2.";
	}
	
	@RequestMapping("/usr/home/main3")
	@ResponseBody
	public String showMain3() {
		return "안녕하세요3.";
	}
	
	@RequestMapping("/usr/home/main4")
	@ResponseBody
	public String showMain4() {
		return "안녕하세요4.";
	}
}