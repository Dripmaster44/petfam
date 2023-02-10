package com.petfam.petfam.controller;

import com.petfam.petfam.dto.comment.CommentRequestDto;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.service.comment.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

  private final CommentServiceImpl commentService;


  // 댓글 생성
  @PostMapping("/{postId}")
  public ResponseEntity<String> Comment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK).body(commentService.comment(postId, userDetails.getUser(), commentRequestDto));
  }

  // 댓글 수정
  @PatchMapping("/{commentId}")
  public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(commentId, userDetails.getUser(), commentRequestDto));
  }

  // 댓글 삭제
  @DeleteMapping("/{commentId}")
  public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK).body(commentService.deleteComment(commentId, userDetails.getUser()));
  }
}
