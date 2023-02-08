package com.petfam.petfam.dto;

import com.petfam.petfam.entity.Post;

import lombok.Getter;

@Getter
public class CreatePostRequestDto {

	private	String title;
	private String content;
	private String image;


}
