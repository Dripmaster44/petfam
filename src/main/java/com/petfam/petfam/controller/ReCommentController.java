package com.petfam.petfam.controller;

import com.petfam.petfam.dto.recomment.ReCommentRequestDto;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.service.recomment.ReCommentService;
import com.petfam.petfam.service.recomment.ReCommentServiceImpl;
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
@RequestMapping("/recomments")
public class ReCommentController {

  private final ReCommentServiceImpl reCommentService;

  // 대댓글 생성
  @PostMapping("commentId")
  public void reComment(@PathVariable Long commentId, ReCommentRequestDto reCommentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    reCommentService.reComment(commentId, userDetails.getUser(), reCommentRequestDto);
  }

  // 대댓글 수정
  @PatchMapping("/{recommentId}")
  public void updateReComment(@PathVariable Long recommentId, ReCommentRequestDto reCommentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    reCommentService.updateReComment(recommentId, userDetails.getUser(), reCommentRequestDto);
  }

  // 대댓글 삭제
  @DeleteMapping("/{recommentId}")
  public void deleteReComment(@PathVariable Long recommentId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    reCommentService.deleteReComment(recommentId, userDetails.getUser());
  }

}
