package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

import lombok.Data;

@Controller
public class UsrHomeController {
	
	@RequestMapping("/usr/home/getInt")
	@ResponseBody
	public int getInt() {
		return 10;
	}
	
	@RequestMapping("/usr/home/getFloat")
	@ResponseBody
	public float getFloat() {
		return 10.5f;
	}
	
	@RequestMapping("/usr/home/getBoolean")
	@ResponseBody
	public boolean getBoolean() {
		return true;
	}
	
	@RequestMapping("/usr/home/getCharacter")
	@ResponseBody
	public char getCharacter() {
		return 'a';
	}
	
	@RequestMapping("/usr/home/getMap")
	@ResponseBody
	public Map getMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("철수나이", 22);
		map.put("영희나이", 33);
		
		return map;
		// 출력 : {"철수나이":22,"영희나이":33}
	}
	
	@RequestMapping("/usr/home/getList")
	@ResponseBody
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		list.add("철수");
		list.add("영희");
		
		return list;
		// 출력 : ["철수","영희"]
	}
	
}
