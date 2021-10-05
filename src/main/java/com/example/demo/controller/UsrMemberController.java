package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	private MemberService memberService;
	
	private UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		if (Util.empty(loginId)) {
			return ResultData.from("F-1", "loginId(을)를 입력해주세요.");
		}

		if (Util.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw(을)를 입력해주세요.");
		}

		if (Util.empty(name)) {
			return ResultData.from("F-3", "name(을)를 입력해주세요.");
		}

		if (Util.empty(nickname)) {
			return ResultData.from("F-4", "nickname(을)를 입력해주세요.");
		}

		if (Util.empty(cellphoneNo)) {
			return ResultData.from("F-5", "cellphoneNo(을)를 입력해주세요.");
		}

		if (Util.empty(email)) {
			return ResultData.from("F-6", "email(을)를 입력해주세요.");
		}
		
		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		if ( id == -1 ) {
			return ResultData.from("F-7", Util.f("해당 로그인아이디(%s)는 이미 사용중입니다.", loginId));
		}
		
		if ( id == -2 ) {
			return ResultData.from("F-8", Util.f("해당 이메일(%s)은 이미 사용중입니다.", email));
		}
		
		if ( id == -3 ) {
			return ResultData.from("F-9", Util.f("해당 전화번호(%s)는 이미 사용중입니다.", cellphoneNo));
		}
		
		Member member = memberService.getMemberById(id);
		return ResultData.from("S-1", Util.f("회원가입에 성공하였습니다.", name, email), member);
	}

	
}
