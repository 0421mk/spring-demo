package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	// @Autowired시 생성자에 articleService = new ArticleService(); 안해도됨
	// 서비스나 Dao, Dto만 달아야 한다.
	private ArticleService articleService;
	
	private UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	// 액션 메서드 시작
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	private ResultData doAdd(String title, String body) {
		if(Util.empty(title)) {
			return ResultData.from("F-1", "title(을)를 입력해주세요.");
		}
		
		if(Util.empty(body)) {
			return ResultData.from("F-1", "body(을)를 입력해주세요.");
		}
		
		articleService.writeArticle(title, body);
		int id = articleService.getLastIndexId();
		
		return ResultData.from("S-1", Util.f("%d번 게시물이 생성되었습니다.", id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	private String doDelete(int id) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}

		articleService.deleteArticle(id);

		return id + "번 게시물을 삭제하였습니다.";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	private String doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}

		articleService.modifyArticle(id, title, body);

		return id + "번 게시물을 수정하였습니다.";
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	private List<Article> getArticles() {

		return articleService.getArticles();
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	private ResultData getArticle(int id) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			
			return ResultData.from("F-1", Util.f("%d번 게시물이 존재하지 않습니다.", id));
		}

		// return 값이 String 일수도 있고 Object 일 수도 있어서 모든 것을 통칭하는 Object 리턴 자료형으로 설정

		return ResultData.from("S-1", Util.f("%d번 게시물이 존재합니다.", id), article);
	}
	// 액션 메서드 종료

}