package com.petfam.petfam.service;

import com.petfam.petfam.dto.ReCommentRequestDto;

public interface ReCommentService {

    // 대댓글 생성
    String reComment(Long commentId, String username, ReCommentRequestDto reCommentRequestDto);

    // 대댓글 수정
    String updateReComment(Long commentId, Long reCommentId, String username, ReCommentRequestDto reCommentRequestDto);

    // 대댓글 삭제
    String deleteReComment(Long reCommentId, Long commentId, String username, ReCommentRequestDto reCommentRequestDto);

}
