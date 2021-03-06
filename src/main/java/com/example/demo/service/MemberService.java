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

		member = memberRepository.getMemberByCellphonNo(cellphoneNo);

		if (member != null) {
			return -3;
		}

		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		return memberRepository.getLastIndexId();
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public void doModify(int id, String nickname) {
		
		memberRepository.doModify(id, nickname);
		
	}

	public void doModifyPw(int id, String loginPw) {
		
		memberRepository.doModifyPw(id, loginPw);
		
	}

}
