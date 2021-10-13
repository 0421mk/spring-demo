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
public interface LikeRepository {
	
	public void doLike(int nowLoginedMemberId, int articleId, int likeVal);

	public int getLikeCountByMemberId(int nowLoginedMemberId);

	public Liketable getLiketableByMemberId(int nowLoginedMemberId, int articleId);

	public void modifyLike(int nowLoginedMemberId, int articleId, int likeVal);

	public int getLikeValue(int articleId, int likeType);
}
