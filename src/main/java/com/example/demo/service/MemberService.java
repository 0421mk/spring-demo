package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {
//	private MemberRepository memberRepository;
//	
//	private MemberService(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}

	public String join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		return "성공";
//		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
	}

}
