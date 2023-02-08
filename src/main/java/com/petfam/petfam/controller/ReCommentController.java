package com.petfam.petfam.controller;

import com.petfam.petfam.dto.ReCommentRequestDto;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.service.ReCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recomments")
public class ReCommentController {

    private final ReCommentService reCommentService;

    // 대댓글 생성
    @PostMapping("")
    public void reComment(Long Id, ReCommentRequestDto reCommentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reCommentService.reComment(Id,userDetails.getUsername(), reCommentRequestDto);
    }

    // 대댓글 수정
    @PatchMapping("/{Id}")
    public void updateReComment(@PathVariable Long Id, ReCommentRequestDto reCommentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reCommentService.updateReComment(Id, userDetails.getUsername(),reCommentRequestDto);
    }

    // 대댓글 삭제
    @DeleteMapping("/{Id}")
    public void deleteReComment(@PathVariable Long Id, ReCommentRequestDto reCommentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reCommentService.deleteReComment(Id, userDetails.getUsername(),reCommentRequestDto);
    }

}
