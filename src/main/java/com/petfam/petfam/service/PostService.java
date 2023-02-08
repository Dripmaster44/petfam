package com.petfam.petfam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petfam.petfam.dto.AllPostResponseDto;
import com.petfam.petfam.dto.CreatePostRequestDto;
import com.petfam.petfam.dto.PostRequestDto;
import com.petfam.petfam.dto.PostResponseDto;
import com.petfam.petfam.dto.UpdatePostResponseDto;

@Service
public interface PostService {

	//createPost
	String createPost (CreatePostRequestDto requestDto);

	// getAllPosts
	List<AllPostResponseDto> getAllPosts();

	// getSelectPost
	PostResponseDto getSelectPost(Long postId);

	// updatePost
	UpdatePostResponseDto updatePost (Long postId, PostRequestDto requestDto);


	// deletePost
	String deletePost (Long postId);



}
