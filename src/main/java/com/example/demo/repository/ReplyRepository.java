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
import com.example.demo.vo.Reply;

@Mapper
public interface ReplyRepository {
	@Insert("""
			INSERT INTO reply
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{loginedMemberId},
			articleId = #{articleId},
			body = #{body},
			replyType = #{replyType},
			reReplyId = 0
			""")
	void doWrite(int articleId, String body, int loginedMemberId, int replyType);

	@Select("""
			SELECT A.*, B.nickname AS extra_writerName
			FROM reply AS A
			INNER JOIN `member` AS B
			ON A.memberId = B.id
			WHERE A.articleId = #{id}
			AND A.replyType = #{replyType}
			ORDER BY A.id ASC
						""")
	List<Reply> getReplies(int id, int replyType);

	@Select("""
			SELECT R.*, M.name AS extra_writerName
			FROM reply AS R
			LEFT JOIN `member` AS M
			ON R.memberId = M.id
			WHERE R.id = #{id}
						""")
	Reply getReplyById(int id);

	@Delete("""
			DELETE FROM reply
			WHERE id = #{id}
						""")
	void doDelete(int id);
	
	@Update("""
			UPDATE reply
			SET body = #{body},
			updateDate = NOW()
			WHERE id = #{id}
			""")
	void modifyArticle(int id, String body);

}
