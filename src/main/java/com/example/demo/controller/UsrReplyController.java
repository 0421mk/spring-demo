package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Rq;

@Controller
public class UsrReplyController {
	private ReplyService replyService;
	private Rq rq;

	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	private String doWrite(int articleId, String body, String replyType) {
		
		int replyTypeVal = Integer.parseInt(replyType);
		int loginedMemberId = rq.getLoginedMemberId();
		
		replyService.doWrite(articleId, body, loginedMemberId, replyTypeVal);
			
		return Util.jsReplace(null, "/usr/article/detail?id=" + articleId);
	}
}
