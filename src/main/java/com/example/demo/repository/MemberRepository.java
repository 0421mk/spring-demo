package com.example.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Member;

@Mapper
public interface MemberRepository {
	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw =  #{loginPw},
			`name` =  #{name},
			nickname =  #{nickname},
			cellphoneNo =  #{cellphoneNo},
			email =  #{email}
			""")
	public void join(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name,
			@Param("nickname") String nickname, @Param("cellphoneNo") String cellphoneNo, @Param("email") String email);
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastIndexId();

	@Select("SELECT * FROM `member` AS M WHERE M.id = #{id}")
	public Member getMemberById(@Param("id") int id);

	@Select("SELECT * FROM `member` AS M WHERE M.loginId = #{loginId}")
	public Member getMemberByLoginId(@Param("loginId") String loginId);

	@Select("SELECT * FROM `member` AS M WHERE M.email = #{email}")
	public Member getMemberByEmail(@Param("email") String email);

	@Select("SELECT * FROM `member` AS M WHERE M.cellphoneNo = #{cellphoneNo}")
	public Member getMemberByCellphonNo(@Param("cellphoneNo") String cellphoneNo);

	@Update("""
			UPDATE `member`
			SET nickname =  #{nickname}
			WHERE id = #{id}
			""")
	public void doModify(int id, String nickname);
	
	@Update("""
			UPDATE `member`
			SET loginPw =  #{loginPw}
			WHERE id = #{id}
			""")
	public void doModifyPw(int id, String loginPw);

}
