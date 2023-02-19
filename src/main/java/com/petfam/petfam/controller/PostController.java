package com.petfam.petfam.controller;

import com.petfam.petfam.dto.post.AllPostResponseDto;
import com.petfam.petfam.dto.post.PostCreateRequestDto;
import com.petfam.petfam.dto.post.PostResponseDto;
import com.petfam.petfam.dto.post.PostUpdateRequestDto;
import com.petfam.petfam.entity.Post;
import com.petfam.petfam.repository.PostRepository;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.service.post.PostServiceImpl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

  private final PostServiceImpl postService;
  private final PostRepository postRepository;

  // 게시글 작성
  @PostMapping("")
  public String createPost(@RequestBody PostCreateRequestDto postCreateRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return postService.createPost(postCreateRequestDto, userDetails.getUser());
  }

  // 게시글 전체 목록 조회
  @GetMapping("")
  public Page<AllPostResponseDto> getAllPosts(Pageable pageable) {
    return postService.getAllPosts(pageable);
  }

  // 선택 게시글 조회
  @GetMapping("/{id}")
  public PostResponseDto getSelectPost(@PathVariable Long id) {
    return postService.getSelectPost(id);
  }

  @PatchMapping("/{id}")
  public String updatePost(@PathVariable Long id,
      @RequestBody PostUpdateRequestDto postUpdateRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    postService.updatePost(id, postUpdateRequestDto, userDetails.getUser());
    return "게시글 수정이 완료되었습니다.";
  }

  @DeleteMapping("/{id}")
  public String deletePost(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return postService.deletePost(id, userDetails.getUser());
  }

  @GetMapping("/topThree")
  public List<PostResponseDto> getTopThreePosts(){
    return postService.getTopThreePosts();
  }

}
