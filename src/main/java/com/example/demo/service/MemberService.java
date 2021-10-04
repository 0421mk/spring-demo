package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;

@Service
public class MemberService {
	private MemberRepository memberRepository;
	
	private MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		Member member = memberRepository.getMemberByLoginId(loginId);
		
		if (member != null) {
			return -1;
		}
		
		member = memberRepository.getMemberByEmail(email);
		
		if (member != null) {
			return -2;
		}
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		return memberRepository.getLastIndexId();
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

}
