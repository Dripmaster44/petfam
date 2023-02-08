package com.petfam.petfam.service.recomment;

import com.petfam.petfam.dto.recomment.ReCommentRequestDto;

public interface ReCommentService {

  // 대댓글 생성
  String reComment(Long commentId, String username, ReCommentRequestDto reCommentRequestDto);

  // 대댓글 수정
  String updateReComment(Long reCommentId, String username,
      ReCommentRequestDto reCommentRequestDto);

  // 대댓글 삭제
  String deleteReComment(Long reCommentId, String username,
      ReCommentRequestDto reCommentRequestDto);

}
