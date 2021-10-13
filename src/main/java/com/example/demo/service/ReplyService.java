package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ReplyRepository;
import com.example.demo.vo.Reply;

@Service
public class ReplyService {
	private ReplyRepository replyRepository;

	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	public void doWrite(int articleId, String body, int loginedMemberId, int replyType) {
		replyRepository.doWrite(articleId, body, loginedMemberId, replyType);
	}

	public List<Reply> getReplies(int id, int replyType) {
		return replyRepository.getReplies(id, replyType);
	}
}
