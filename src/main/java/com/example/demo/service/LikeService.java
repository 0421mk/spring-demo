package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.LikeRepository;
import com.example.demo.vo.Liketable;

@Service
public class LikeService {
	private LikeRepository likeRepository;

	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}

	public void doLike(int nowLoginedMemberId, int articleId, int likeVal) {

		likeRepository.doLike(nowLoginedMemberId, articleId, likeVal);
	}

	public int getLikeCountByMemberId(int nowLoginedMemberId) {
		return likeRepository.getLikeCountByMemberId(nowLoginedMemberId);
	}

	public Liketable getLiketableByMemberId(int nowLoginedMemberId, int articleId) {
		return likeRepository.getLiketableByMemberId(nowLoginedMemberId, articleId);
	}

	public void modifyLike(int nowLoginedMemberId, int articleId, int likeVal) {
		likeRepository.modifyLike(nowLoginedMemberId, articleId, likeVal);
	}

	public int getLikeValue(int articleId, int likeType) {
		return likeRepository.getLikeValue(articleId, likeType);
	}
}
