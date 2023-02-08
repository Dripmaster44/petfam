package com.petfam.petfam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petfam.petfam.dto.AllPostResponseDto;
import com.petfam.petfam.dto.PostCreateRequestDto;
import com.petfam.petfam.dto.PostUpdateRequestDto;
import com.petfam.petfam.dto.PostResponseDto;
import com.petfam.petfam.dto.PostUpdateResponseDto;

@Service
public interface PostService {

	//createPost
	String createPost (PostCreateRequestDto requestDto);

	// getAllPosts
	List<AllPostResponseDto> getAllPosts();

	// getSelectPost
	PostResponseDto getSelectPost(Long postId);

	// updatePost
	PostUpdateResponseDto updatePost (Long postId, PostUpdateRequestDto requestDto);


	// deletePost
	String deletePost (Long postId);


}
