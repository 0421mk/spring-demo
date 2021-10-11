package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.Liketable;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrArticleController {
	// @Autowired시 생성자에 articleService = new ArticleService(); 안해도됨
	// 서비스나 Dao, Dto만 달아야 한다.
	private ArticleService articleService;
	private BoardService boardService;
	private Rq rq;

	private UsrArticleController(ArticleService articleService, BoardService boardService, Rq rq) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
	}

	// 액션 메서드 시작
	@RequestMapping("/usr/article/write")
	private String showWrite(HttpServletRequest req) {

		return "usr/article/write";
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	private String doWrite(int boardId, String title, String body) {

		if (Util.empty(title)) {
			return Util.jsHistoryBack("title(을)를 입력해주세요.");
		}

		if (Util.empty(body)) {
			return Util.jsHistoryBack("body(을)를 입력해주세요.");
		}

		int id = rq.getLoginedMemberId();
		articleService.writeArticle(id, title, body, boardId);

		Article article = articleService.getArticleByMemberId(id);
		int articleId = article.getId();
		String replaceUri = "../article/detail?id=" + articleId;

		return Util.jsReplace(Util.f("%d번 게시물이 생성되었습니다.", articleId), replaceUri);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	private String doDelete(int id) {

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
	private String showModify(Model model, int id) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return rq.historyBackJsOnView(Util.f("%d번 게시물이 존재하지 않습니다.", id));
		}

		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return rq.historyBackJsOnView("권한이 없습니다.");
		}

		model.addAttribute("article", article);

		return "usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	private String doModify(int id, String title, String body) {

		Article article = articleService.getArticle(id);

		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물이 존재하지 않습니다.", id));
		}

		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("권한이 없습니다.");
		}

		articleService.modifyArticle(id, title, body);

		return Util.jsReplace(Util.f("%d번 게시물을 수정했습니다.", id), "../article/list");
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
	private String showList(@RequestParam(defaultValue = "1") int boardId,
			@RequestParam(defaultValue = "default") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "1") int page,
			Model model) {

		int itemsInAPage = 10;

		List<Article> articles = articleService.getArticlesListPage(boardId, itemsInAPage, page, searchKeywordTypeCode,
				searchKeyword);
		Board board = boardService.getBoardById(boardId);

		if (board == null) {
			return rq.historyBackJsOnView("존재하지 않는 게시판입니다.");
		}

		int articleCount = articleService.getArticleCount(boardId, searchKeywordTypeCode, searchKeyword);

		int pagesCount = (int) Math.ceil((double) articleCount / itemsInAPage);

		model.addAttribute("page", page);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("articleCount", articleCount);
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);

		return "usr/article/list";
	}

	@RequestMapping("/usr/article/doLike")
	@ResponseBody
	private String doLike(@RequestBody Map<String, String> likeData) {
		
		int nowLoginedMemberId = rq.getLoginedMemberId();

		if(nowLoginedMemberId == 0) {
			return "0";
		}
		
		int likeVal = Integer.parseInt(likeData.get("value"));
		int articleId = Integer.parseInt(likeData.get("articleId"));
				
		Liketable liketable = articleService.getLiketableByMemberId(nowLoginedMemberId, articleId);
		
		// 유저가 각각 게시물(articleId)에 추천을 했을 경우 liketable이 null 이 아님
		if(liketable != null && liketable.getPoint() == likeVal) {
			if(likeVal == 1) {
				return "1";
			} else if(likeVal == -1) {
				return "2";
			}
		}

		if(liketable != null && liketable.getPoint() != likeVal) {
			articleService.modifyLike(nowLoginedMemberId, articleId, likeVal);
			// Gson으로 JSON RESPONSE 하고 제이쿼리로 텍스트 기입 적용
			return "3";
		}
		
		articleService.doLike(nowLoginedMemberId, articleId, likeVal);
		
		return "4";
	}
	
	@RequestMapping("/usr/article/detail")
	private String showDetail(Model model, int id) {
		articleService.increaseHit(id);
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return rq.historyBackJsOnView("존재하지 않는 게시글입니다.");
		}

		model.addAttribute("article", article);

		return "usr/article/detail";
	}
	// 액션 메서드 종료

}