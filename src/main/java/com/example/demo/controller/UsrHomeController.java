package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping("/usr/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		Article article = new Article();
		
		return article;
	}
}

@Data
class Article {
	private int id;
	private String title;
	// 객체 Article 출력시 public을 써야 하지만 스프링에서는 private 권장
	// 그래서 private 변수 위에 @Getter 를 사용하면 출력 가능
	// 하지만 lombok을 class 위에 @Data로 사용하면 간편하게 사용가능
	// @AllArgsConstructor => 생성자에 인자를 받을 수 있음
	// @NoArgsConstructor => 생성자가 없는 클래스도 받을 수 있음
	
	public Article() {
		id = 1;
		title = "제목1";
	}
}