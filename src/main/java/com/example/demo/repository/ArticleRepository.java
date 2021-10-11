package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Article;
import com.example.demo.vo.Liketable;

@Mapper
public interface ArticleRepository {
	public void writeArticle(@Param("memberId") int loginedMemberId, @Param("title") String title, @Param("body") String body, @Param("boardId") int boardId);

	public Article getArticle(@Param("id") int id);

	public void deleteArticle(@Param("id") int id);

	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	public List<Article> getArticles();
	
	public int getLastInsertId();

	public Article getArticleByMemberId(@Param("memberId") int memberId);

	public List<Article> getArticlesByMemberId(@Param("boardId") int boardId);

	public int getArticleCount(int boardId, String searchKeywordTypeCode, String searchKeyword);

	public List<Article> getArticlesListPage(int boardId, int limitStart, int limitCnt, String searchKeywordTypeCode, String searchKeyword);

	public void increaseHit(int id);

	public void doLike(int nowLoginedMemberId, int articleId, int likeVal);

	public int getLikeCountByMemberId(int nowLoginedMemberId);

	public Liketable getLiketableByMemberId(int nowLoginedMemberId, int articleId);

	public void modifyLike(int nowLoginedMemberId, int articleId, int likeVal);
}
