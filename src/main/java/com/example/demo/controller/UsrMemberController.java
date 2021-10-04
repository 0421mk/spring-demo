package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {
	private MemberService memberService;
	
	private UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		if (Util.empty(loginId)) {
			return "loginId(을)를 입력해주세요.";
		}

		if (Util.empty(loginPw)) {
			return "loginPw(을)를 입력해주세요.";
		}

		if (Util.empty(name)) {
			return "name(을)를 입력해주세요.";
		}

		if (Util.empty(nickname)) {
			return "nickname(을)를 입력해주세요.";
		}

		if (Util.empty(cellphoneNo)) {
			return "cellphoneNo(을)를 입력해주세요.";
		}

		if (Util.empty(email)) {
			return "email(을)를 입력해주세요.";
		}
		
		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		if ( id == -1 ) {
			return "이미 가입된 회원입니다.";
		}
		
		Member member = memberService.getMemberById(id);
		return member;
	}

	
}
