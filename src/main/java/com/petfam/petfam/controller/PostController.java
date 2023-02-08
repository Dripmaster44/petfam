package com.petfam.petfam.controller;

import com.petfam.petfam.dto.post.PostCreateRequestDto;
import com.petfam.petfam.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostServiceImpl postService;

  // 게시글 작성
  @PostMapping("/posts")
  public void createPost(@RequestBody PostCreateRequestDto postCreateRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    postService.createPost(postCreateRequestDto);
  }

  // 게시글 전체 목록 조회
  @GetMapping("/posts")
  public List<AllPostResponseDto> getAllPosts() {
    return postService.getAllPosts();
  }

  // 선택 게시글 조회
  @GetMapping("/posts/{id}")
  public PostResponseDto getSelectPost(@PathVariable Long id) {
    return postService.getSelectPost(id);
  }

  @PatchMapping("/posts/{id}")
  public PostUpdateResponseDto updatePost(@PathVariable Long id,
      @RequestBody PostUpdateRequestDto postUpdateRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return postService.updatePost(id, postUpdateRequestDto);
  }

  @DeleteMapping("/posts/{id}")
  public void deletePost(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    postService.deletePost(id);
  }

}
