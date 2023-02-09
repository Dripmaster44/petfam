package com.petfam.petfam.service.post;

import com.petfam.petfam.dto.post.AllPostResponseDto;
import com.petfam.petfam.dto.post.PostCreateRequestDto;
import com.petfam.petfam.dto.post.PostResponseDto;
import com.petfam.petfam.dto.post.PostUpdateRequestDto;
import com.petfam.petfam.dto.post.PostUpdateResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

  //createPost
  String createPost(PostCreateRequestDto requestDto);

  // getAllPosts
  List<AllPostResponseDto> getAllPosts();

  // getSelectPost
  PostResponseDto getSelectPost(Long postId);

  // updatePost
  PostUpdateResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto);


  // deletePost
  String deletePost(Long postId);


}
