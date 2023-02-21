package com.petfam.petfam.service.post;

import com.petfam.petfam.dto.post.AllPostResponseDto;
import com.petfam.petfam.dto.post.PostCreateRequestDto;
import com.petfam.petfam.dto.post.PostResponseDto;
import com.petfam.petfam.dto.post.PostUpdateRequestDto;
import com.petfam.petfam.dto.post.PostUpdateResponseDto;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.entity.enums.CategoryEnum;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

  //createPost
  String createPost(PostCreateRequestDto requestDto, User user);

  Page<AllPostResponseDto> getAllPosts(Pageable pageable);

  Page<AllPostResponseDto> getPostsByCategory(CategoryEnum category, Pageable pageable);

  // getSelectPost
  PostResponseDto getSelectPost(Long postId);

  // updatePost
  PostUpdateResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto, User user);


  // deletePost
  String deletePost(Long postId, User user);

  List<PostResponseDto> getTopThreePosts();
}
