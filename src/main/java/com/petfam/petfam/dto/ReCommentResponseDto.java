package com.petfam.petfam.dto;

import com.petfam.petfam.entity.ReComment;

public class ReCommentResponseDto {
	private String writer;
	private String conetent;

	public ReCommentResponseDto(ReComment recomment) {
		this.writer = recomment.getUser().getNickname();
		this.conetent = recomment.getContent();
	}
}
