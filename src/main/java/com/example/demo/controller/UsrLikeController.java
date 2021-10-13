package com.example.demo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.service.LikeService;
import com.example.demo.vo.Article;
import com.example.demo.vo.Liketable;
import com.example.demo.vo.Rq;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class UsrLikeController {
	private LikeService likeService;
	private ArticleService articleService;
	private Rq rq;

	public UsrLikeController(LikeService likeService, ArticleService articleService, Rq rq) {
		this.likeService = likeService;
		this.articleService = articleService;
		this.rq = rq;
	}

	@RequestMapping("/usr/like/doLike")
	@ResponseBody
	private String doLike(@RequestBody Map<String, String> likeData) {

		int nowLoginedMemberId = rq.getLoginedMemberId();

		Gson gson = new Gson();
		JsonObject obj = new JsonObject();

		if (nowLoginedMemberId == 0) {
			obj.addProperty("type", "0");
			String json = gson.toJson(obj);

			return json;
		}

		int likeVal = Integer.parseInt(likeData.get("value"));
		int articleId = Integer.parseInt(likeData.get("articleId"));

		Liketable liketable = likeService.getLiketableByMemberId(nowLoginedMemberId, articleId);

		// 유저가 각각 게시물(articleId)에 추천을 했을 경우 liketable이 null 이 아님
		if (liketable != null && liketable.getPoint() == likeVal) {

			if (likeVal == 1) {
				obj.addProperty("type", "1");
				String json = gson.toJson(obj);

				return json;
			} else if (likeVal == -1) {
				obj.addProperty("type", "2");
				String json = gson.toJson(obj);

				return json;
			}
		}

		if (liketable != null && liketable.getPoint() != likeVal) {
			likeService.modifyLike(nowLoginedMemberId, articleId, likeVal);
			Article article = articleService.getArticle(articleId);

			obj.addProperty("type", "3");
			obj.addProperty("likePoint", article.getExtra_likePoint());
			obj.addProperty("disLikePoint", article.getExtra_disLikePoint());

			String json = gson.toJson(obj);

			return json;
		}

		likeService.doLike(nowLoginedMemberId, articleId, likeVal);
		Article article = articleService.getArticle(articleId);

		obj.addProperty("type", "4");
		obj.addProperty("likePoint", article.getExtra_likePoint());
		obj.addProperty("disLikePoint", article.getExtra_disLikePoint());
		String json = gson.toJson(obj);

		return json;
	}
}
