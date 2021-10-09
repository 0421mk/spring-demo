package com.example.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Board;
import com.example.demo.vo.Member;

@Mapper
public interface BoardRepository {
	
	@Select("""
			SELECT * FROM
			board
			WHERE id = #{boardId}
			AND delStatus = 0
			""")
	public Board getBoardById(@Param("boardId") int boardId);
	
}
