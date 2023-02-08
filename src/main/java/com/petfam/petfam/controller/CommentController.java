package com.petfam.petfam.controller;

import com.petfam.petfam.dto.comment.CommentRequestDto;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;


  // 댓글 생성
  @PostMapping("")
  public void Comment(Long Id, CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.comment(Id, userDetails.getUsername(), commentRequestDto);
  }

  // 댓글 수정
  @PatchMapping("/{Id}")
  public void updateComment(@PathVariable Long Id, CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.updateComment(Id, userDetails.getUsername(), commentRequestDto);
  }

  // 댓글 삭제
  @DeleteMapping("/{Id}")
  public void deleteComment(@PathVariable Long Id, CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentService.deleteComment(Id, userDetails.getUsername(), commentRequestDto);
  }

}
