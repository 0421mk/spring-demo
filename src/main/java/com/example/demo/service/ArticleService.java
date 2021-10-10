package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public void writeArticle(int loginedMemberId, String title, String body, int boardId) {
		articleRepository.writeArticle(loginedMemberId, title, body, boardId);
	}

	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}
	
	public Article getArticleByMemberId(int memberId) {
		return articleRepository.getArticleByMemberId(memberId);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public int getLastIndexId() {
		return articleRepository.getLastInsertId();
	}

	public List<Article> getArticlesByMemberId(int boardId) {
		return articleRepository.getArticlesByMemberId(boardId);
	}

	public int getArticleCount(int boardId) {
		return articleRepository.getArticleCount(boardId);
	}
}
