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
public class UsrArticleController {
	private int articleLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		articleLastId = 0;
		articles = new ArrayList<>();
		
		makeTestData();
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {		
		return writeArticle(title, body);;
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		return articles;
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		return id + "번 게시물을 삭제하였습니다.";
	}
	
	private void makeTestData() {
		for( int i = 0; i < 10; i++ ) {
			
			String title = "제목" + i;
			String body = "내용" + i;
			
			writeArticle(title, body);
			
		}
	}
	
	public Article writeArticle(String title, String body) {
		int id = articleLastId + 1;
		Article article = new Article(id, title, body);
		
		articles.add(article);
		articleLastId = id;
		
		return article;
	}
	
}