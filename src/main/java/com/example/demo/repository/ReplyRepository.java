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
public interface ReplyRepository {
	@Insert("""
			INSERT INTO reply
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{loginedMemberId},
			articleId = #{articleId},
			body = #{body},
			replyType = #{replyType}
			""")
	void doWrite(int articleId, String body, int loginedMemberId, int replyType);
	
}
