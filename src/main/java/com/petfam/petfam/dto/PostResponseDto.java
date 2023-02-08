package com.petfam.petfam.dto;

import java.util.List;

import com.petfam.petfam.entity.Comment;
import com.petfam.petfam.entity.Post;

import lombok.Getter;

@Getter
public class PostResponseDto {
	private String title;
	private String image;
	private String writer;
	private Long writerId;
	private Integer likeCnt;
	private String content;
	private List<Comment> comments; // 리스트

	public PostResponseDto(Post post, Integer likeCnt, List<Comment> comments) {
		this.title = post.getTitle();
		this.image = post.getImage();
		this.content = post.getContent();
		this.writer =  post.getUser().getNickname();
		this.writerId = post.getUser().getId();
		this.likeCnt = likeCnt;
		this.comments = comments;
	}
}
