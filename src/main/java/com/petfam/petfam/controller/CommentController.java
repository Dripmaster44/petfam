package com.petfam.petfam.controller;

import com.petfam.petfam.dto.comment.CommentRequestDto;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.service.comment.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

  private final CommentServiceImpl commentService;


  // 댓글 생성
  @PostMapping("{postId}")
  public void Comment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.comment(postId, userDetails.getUser(), commentRequestDto);
  }

  // 댓글 수정
  @PatchMapping("/{commentId}")
  public void updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.updateComment(commentId, userDetails.getUser(), commentRequestDto);
  }

  // 댓글 삭제
  @DeleteMapping("/{commentId}")
  public void deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.deleteComment(commentId, userDetails.getUser());
  }
}
