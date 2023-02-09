package com.petfam.petfam.dto.comment;

import com.petfam.petfam.dto.recomment.ReCommentResponseDto;
import com.petfam.petfam.entity.Comment;
import com.petfam.petfam.entity.ReComment;
import java.util.List;

public class CommentResponseDto {

  private String writer;
  private String content;
  private List<ReCommentResponseDto> reComments;

  public CommentResponseDto(Comment comment) {
    this.writer = comment.getUser().getNickname();
    this.content = comment.getContent();

    List<ReComment> recomments = comment.getReComment();
    for (ReComment reComment : recomments) {
      this.reComments.add(new ReCommentResponseDto(reComment));
    }
  }

}