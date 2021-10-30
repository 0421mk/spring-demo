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

		return Util.jsReplace("", "/");
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin(HttpSession httpSession) {
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/myPage")
	public String showMyPage() {
		return "usr/member/myPage";
	}
	
	@RequestMapping("/usr/member/checkPassword")
	public String showCheckPassword() {
		return "usr/member/checkPassword";
	}
	
	@RequestMapping("/usr/member/doCheckPassword")
	@ResponseBody
	public String doCheckPassword(String loginPw) {
		
		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}
		
		Member loginedMember = rq.getLoginedMember();
		if(!loginedMember.getLoginPw().equals(loginPw)) {
			return Util.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		
		return Util.jsReplace("", "/usr/member/modify");
	}
	
	@RequestMapping("/usr/member/modify")
	public String showModify() {
		return "usr/member/modify";
	}
	
	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(String nickname) {
		
		Member member = rq.getLoginedMember();
		
		if (Util.empty(nickname)) {
			return Util.jsHistoryBack("nickname(을)를 입력해주세요.");
		}
		
		memberService.doModify(member.getId(), nickname);
		
		return Util.jsReplace("회원 정보를 수정하였습니다.", "/usr/member/myPage");
	}
	
	@RequestMapping("/usr/member/modifyPw")
	public String showModifyPw() {
		return "usr/member/modifyPw";
	}
	
	@RequestMapping("/usr/member/doModifyPw")
	@ResponseBody
	public String doModifyPw(String loginPw, String loginPwConfirm) {
		
		Member member = rq.getLoginedMember();
		
		if (!loginPw.equals(loginPwConfirm)) {
			return Util.jsHistoryBack("비밀번호 확인이 같지 않습니다.");
		}
		
		memberService.doModifyPw(member.getId(), loginPw);
		
		return Util.jsReplace("비밀번호를 변경하였습니다.", "/usr/member/myPage");
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
		
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

		return Util.jsReplace("", "/");
	}
	
	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(HttpServletRequest req, String loginId, String loginPw, String loginPwConfirm, String name, String nickname, String cellphoneNo,
			String email) {

		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("loginId(을)를 입력해주세요.");
		}

		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}
		
		if (Util.empty(loginPwConfirm)) {
			return Util.jsHistoryBack("loginPwConfirm(을)를 입력해주세요.");
		}
		
		if(!loginPw.equals(loginPwConfirm)) {
			return Util.jsHistoryBack("비밀번호를 확인해주세요.");
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
