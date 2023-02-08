package com.petfam.petfam.dto;


import com.petfam.petfam.entity.Post;

public class UpdatePostResponseDto {

	private String title;
	private String content;
	private String image;

	public UpdatePostResponseDto(Post post){
		this.title = post.getTitle();
		this.content = post.getContent();
		this.image = post.getImage();
	}
}
