package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrMemberController {
	private MemberService memberService;
	private Rq rq;

	private UsrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody //이게 있어야 Body로 return 값이 노출된다. 이게 없으면 Url로 인식
	public String doLogout() {
		rq.logout();

		return Util.jsReplace("로그아웃 되었습니다.", "/");
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin(HttpSession httpSession) {
		return "usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw) {
		
		if (rq.isLogined()) {
			return Util.jsHistoryBack("로그아웃 후 이용해주세요.");
		}

		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("loginId(을)를 입력해주세요.");
		}

		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			return Util.jsHistoryBack("존재하지 않는 아이디입니다.");
		}
		
		String memberNickname = member.getNickname();

		// equals로 처리해야한다.
		if (member.getLoginPw().equals(loginPw) == false) {
			return Util.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		
		rq.login(member);

		return Util.jsReplace(Util.f("로그인에 성공하였습니다. 환영합니다 %s님!", member.getNickname()), "/");
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(HttpServletRequest req, String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {

		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("loginId(을)를 입력해주세요.");
		}

		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}

		if (Util.empty(name)) {
			return Util.jsHistoryBack("name(을)를 입력해주세요.");
		}

		if (Util.empty(nickname)) {
			return Util.jsHistoryBack("nickname(을)를 입력해주세요.");
		}

		if (Util.empty(cellphoneNo)) {
			return Util.jsHistoryBack("cellphoneNo(을)를 입력해주세요.");
		}

		if (Util.empty(email)) {
			return Util.jsHistoryBack("email(을)를 입력해주세요.");
		}

		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (id == -1) {
			return Util.jsHistoryBack(Util.f("해당 로그인아이디(%s)는 이미 사용중입니다.", loginId));
		}

		if (id == -2) {
			return Util.jsHistoryBack(Util.f("해당 이메일(%s)은 이미 사용중입니다.", email));
		}

		if (id == -3) {
			return Util.jsHistoryBack(Util.f("해당 전화번호(%s)는 이미 사용중입니다.", cellphoneNo));
		}

		Member member = memberService.getMemberById(id);
		
		Rq rq = (Rq) req.getAttribute("rq");
		rq.login(member);
		
		return Util.jsReplace("회원가입에 성공하였습니다.", "/");
		
	}
}
