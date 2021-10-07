package com.example.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import com.example.demo.util.Util;

import lombok.Getter;

public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		
		// HttpServletRequest에서 session을 얻을 수 있다.
		boolean isLogined = false;
		int loginedMemberId = 0;
		this.session = req.getSession();

		if (this.session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) this.session.getAttribute("loginedMemberId");
		}

		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;

	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}
	
	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
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
	
	public void println(String str) {
		try {
			this.resp.getWriter().append(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
