package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.util.Util;
import com.example.demo.vo.Rq;

@Component("needLoginInterceptor")
public class NeedLoginInterceptor implements HandlerInterceptor {
	private Rq rq;
	
	public NeedLoginInterceptor(Rq rq) {
		this.rq = rq;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {				
		
		if (!rq.isLogined()) {
			rq.printJsReplace("로그인 후 이용해주세요.", "/usr/member/login");
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}