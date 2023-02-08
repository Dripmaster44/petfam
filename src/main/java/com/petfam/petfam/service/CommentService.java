package com.petfam.petfam.service;

import com.petfam.petfam.dto.CommentRequestDto;

public interface CommentService {
    // 댓글 생성
    String comment(Long postId, String username, CommentRequestDto commentRequestDto);

    // 댓글 수정
    String updateComment(Long commentId, Long postId, String username, CommentRequestDto commentRequestDto);

    // 댓글 삭제
    String deleteComment(Long commentId, Long postId, String username, CommentRequestDto commentRequestDto);
}
