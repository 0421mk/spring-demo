package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
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
	
	@RequestMapping("/usr/reply/modify")
	private String modify(int id, Model model) {
		
		Reply reply = replyService.getReplyById(id);
		
		if(reply == null) {
			return rq.historyBackJsOnView("댓글이 존재하지 않습니다.");
		}
		
		if(reply.getMemberId() != rq.getLoginedMemberId()) {
			return rq.historyBackJsOnView("수정할 수 있는 권한이 존재하지 않습니다.");
		}
		
		model.addAttribute("reply", reply);
			
		return "usr/reply/modify";
	}
	
	@RequestMapping("/usr/reply/doModify")
	@ResponseBody
	private String doModify(int id, String body) {
		Reply reply = replyService.getReplyById(id);

		if (reply == null) {
			return Util.jsHistoryBack(Util.f("%d번 댓글이 존재하지 않습니다.", id));
		}

		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("권한이 없습니다.");
		}

		replyService.modifyArticle(id, body);

		return Util.jsReplace(Util.f("댓글을 수정하였습니다."), Util.f("../article/detail?id=%d", reply.getArticleId()));
	
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
	private String doDelete(int id) {
		
		Reply reply = replyService.getReplyById(id);
		
		if(reply == null) {
			return Util.jsHistoryBack("댓글이 존재하지 않습니다.");
		}
		
		if(reply.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("삭제할 수 있는 권한이 존재하지 않습니다.");
		}
		
		replyService.doDelete(id);
			
		return Util.jsReplace(Util.f("댓글을 삭제하였습니다."), Util.f("../article/detail?id=%d", reply.getArticleId()));
	}
}
