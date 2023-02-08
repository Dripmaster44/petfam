package com.petfam.petfam.service.comment;

import com.petfam.petfam.dto.comment.CommentRequestDto;

public interface CommentService {

  // 댓글 생성
  String comment(Long postId, String username, CommentRequestDto commentRequestDto);

  // 댓글 수정
  String updateComment(Long commentId, String username, CommentRequestDto commentRequestDto);

  // 댓글 삭제
  String deleteComment(Long commentId, String username, CommentRequestDto commentRequestDto);
}
