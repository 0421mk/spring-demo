package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ReplyRepository;

@Service
public class ReplyService {
	private ReplyRepository replyRepository;

	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	public void doWrite(int articleId, String body, int loginedMemberId, int replyType) {
		replyRepository.doWrite(articleId, body, loginedMemberId, replyType);
	}
}
