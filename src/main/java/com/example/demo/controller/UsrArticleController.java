package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrArticleController {
	// @Autowired시 생성자에 articleService = new ArticleService(); 안해도됨
	// 서비스나 Dao, Dto만 달아야 한다.
	private ArticleService articleService;

	private UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	// 액션 메서드 시작
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	private ResultData doWrite(HttpServletRequest req, String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if (Util.empty(title)) {
			return ResultData.from("F-2", "title(을)를 입력해주세요.");
		}

		if (Util.empty(body)) {
			return ResultData.from("F-3", "body(을)를 입력해주세요.");
		}

		int id = rq.getLoginedMemberId();
		articleService.writeArticle(id, title, body);

		return ResultData.from("S-1", Util.f("%d번 게시물이 생성되었습니다.", id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	private String doDelete(HttpServletRequest req, int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getArticle(id);

		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물이 존재하지 않습니다.", id));
		}

		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("권한이 없습니다.");
		}

		articleService.deleteArticle(id);

		return Util.jsReplace(Util.f("%d번 게시물을 삭제하였습니다.", id), "../article/list");
	}

	@RequestMapping("/usr/article/modify")
	private String showModify(HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getArticle(id);

		if (article == null) {
			return rq.historyBackJsOnView(Util.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return rq.historyBackJsOnView("권한이 없습니다.");
		}
		
		return "usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	private ResultData doModify(HttpServletRequest req, int id, String title, String body) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-2", Util.f("%d번 게시물이 존재하지 않습니다.", id));
		}

		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return ResultData.from("F-3", "권한이 없습니다.");
		}

		articleService.modifyArticle(id, title, body);

		return ResultData.from("S-1", Util.f("%d번 게시물을 수정하였습니다.", id));
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	private ResultData getArticles() {
		List<Article> articles = articleService.getArticles();

		return ResultData.from("S-1", "게시물 리스트입니다.", articles);
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

	@RequestMapping("/usr/article/list")
	private String showList(Model model) {
		List<Article> articles = articleService.getArticles();

		model.addAttribute("articles", articles);

		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	private String showDetail(Model model, int id) {
		Article article = articleService.getArticle(id);

		model.addAttribute("article", article);

			return "usr/article/detail";
		}
	// 액션 메서드 종료

}