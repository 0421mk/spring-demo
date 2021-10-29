package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Reply;
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
	private String doWrite(int articleId, String body, int replyType) {
		
		int loginedMemberId = rq.getLoginedMemberId();
		
		replyService.doWrite(articleId, body, loginedMemberId, replyType);
			
		return Util.jsReplace(null, "/usr/article/detail?id=" + articleId);
	}
	
	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	private String doDelete(int articleId, int replyId) {
		
		Reply reply = replyService.getReplyById(replyId);
		
		if(reply == null) {
			return Util.jsHistoryBack("댓글이 존재하지 않습니다.");
		}
		
		if(reply.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("삭제할 수 있는 권한이 존재하지 않습니다.");
		}
		
		replyService.doDelete(replyId);
			
		return Util.jsReplace(Util.f("댓글을 삭제하였습니다."), Util.f("../article/detail?id=%d", articleId));
	}
}
