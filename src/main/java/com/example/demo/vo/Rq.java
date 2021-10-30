package com.example.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;

import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;

	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		this.req = req;
		this.resp = resp;
		
		// HttpServletRequest에서 session을 얻을 수 있다.
		boolean isLogined = false;
		int loginedMemberId = 0;
		this.session = req.getSession();
		Member loginedMember = null;

		if (this.session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) this.session.getAttribute("loginedMemberId");
			loginedMember = memberService.getMemberById(loginedMemberId);
		}

		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		
		this.req.setAttribute("rq", this);
	}

	public void logout() {
		this.session.removeAttribute("loginedMemberId");
	}
	
	public void login(Member member) {
		this.session.setAttribute("loginedMemberId", member.getId());
	}

	public void printHistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");

		println("<script>");

		if (!Util.empty(msg)) {
			println("alert('" + msg + "');");
		}

		println("history.back();");

		println("</script>");
	}
	
	public void printJsReplace(String msg, String uri) {
		resp.setContentType("text/html; charset=UTF-8");

		println("<script>");

		if (!Util.empty(msg)) {
			println("alert('" + msg + "');");
		}

		println("location.replace('" + uri + "');");

		println("</script>");
	}

	
//	public void printHistoryBackJs(String msg) {
//		println(Util.jsHistoryBack(msg));
//	} 이렇게도 사용 가능
	
	public void println(String str) {
		try {
			this.resp.getWriter().append(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String historyBackJsOnView(String msg) {
		this.req.setAttribute("msg", msg);
		this.req.setAttribute("historyBack", true);
		return "usr/common/js";
	}

	// rq 생성자 실행 유도
	public void init() {
		
	}
}
