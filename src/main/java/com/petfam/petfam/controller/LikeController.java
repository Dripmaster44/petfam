package com.petfam.petfam.controller;

import com.petfam.petfam.dto.like.CommentLikeResponseDto;
import com.petfam.petfam.dto.like.PostLikeResponseDto;
import com.petfam.petfam.dto.like.ReCommentLikeResponseDto;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.service.like.LikeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class LikeController {

  private final LikeServiceImpl likeService;

  // 게시글 좋아요
  @PostMapping("/posts/{postId}/like")
  public PostLikeResponseDto likePost(@PathVariable Long postId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return likeService.likePost(postId, userDetails.getUser());
  }

  // 댓글 좋아요
  @PostMapping("/comments/{commentId}/like")
  public CommentLikeResponseDto likeComment(@PathVariable Long commentId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return likeService.likeComment(commentId, userDetails.getUser());
  }

  // 대댓글 좋아요
  @PostMapping("/recomments/{recommentId}/like")
  public ReCommentLikeResponseDto likeReComment(@PathVariable Long recommentId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return likeService.likeReComment(recommentId, userDetails.getUser());
  }
}
