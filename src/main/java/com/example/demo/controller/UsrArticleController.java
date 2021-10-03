package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {
	//인스턴스 변수 시작
	//@Autowired시 생성자에 articleService = new ArticleService(); 안해도됨
	//서비스나 Dao, Dto만 달아야 한다.
	@Autowired
	private ArticleService articleService;
	private int articleLastId;
	private List<Article> articles;
	//인스턴스 변수 종료
	
	//생성자
	public UsrArticleController() {
		articleLastId = 0;
		articles = new ArrayList<>();
		
		makeTestData();
	}
	
	//서비스 메서드 시작
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		return articles;
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticleAction(int id) {
		Article article = getArticle(id);
		
		if(article == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}
		
		//return 값이 String 일수도 있고 Object 일 수도 있어서 모든 것을 통칭하는 Object 리턴 자료형으로 설정
		
		return article;
	}
	
	private void makeTestData() {
		for( int i = 1; i <= 10; i++ ) {
			
			String title = "제목" + i;
			String body = "내용" + i;
			
			writeArticle(title, body);
			
		}
	}
	
	private Article writeArticle(String title, String body) {
		int id = articleLastId + 1;
		Article article = new Article(id, title, body);
		
		articles.add(article);
		articleLastId = id;
		
		return article;
	}
	
	private Article getArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		
		return null;
	}
	
	private void deleteArticle(int id) {
		Article article = getArticle(id);
		
		articles.remove(article);
	}
	
	private void modifyArticle(int id, String title, String body) {
		Article article = getArticle(id);
		
		article.setTitle(title);
		article.setBody(body);
		
	}
	//서비스 메서드 종료
	
	//액션 메서드 시작
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	private Article doAdd(String title, String body) {		
		return writeArticle(title, body);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	private String doDelete(int id) {
		Article article = getArticle(id);
		
		if (article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}
		
		deleteArticle(id);
		
		return id + "번 게시물을 삭제하였습니다.";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	private String doModify(int id, String title, String body) {
		Article article = getArticle(id);
		
		if (article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}
		
		modifyArticle(id, title, body);
		
		return id + "번 게시물을 수정하였습니다.";
	}
	//액션 메서드 종료
	
}