package com.example.demo.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Getter;

public class Rq {
	@Getter
	private boolean logined = false;
	@Getter
	private int loginedMemberId = 0;

	public Rq(HttpServletRequest req) {
		// HttpServletRequest에서 session을 얻을 수 있다.
		HttpSession httpSession = req.getSession();

		if (httpSession.getAttribute("loginedMemberId") != null) {
			this.logined = true;
			this.loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
	}
}
