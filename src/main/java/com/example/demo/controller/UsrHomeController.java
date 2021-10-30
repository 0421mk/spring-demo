package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.util.Util;
import com.example.demo.vo.Article;

import lombok.Data;

@Controller
public class UsrHomeController {

	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return Util.jsReplace("", "/usr/article/list");
	}

	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}

}
