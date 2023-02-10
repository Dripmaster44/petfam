package com.petfam.petfam.dto.comment;

import com.petfam.petfam.dto.recomment.ReCommentResponseDto;
import com.petfam.petfam.entity.Comment;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class CommentResponseDto {

  private String writer;
  private String content;

  private Integer likes;
  private List<ReCommentResponseDto> reComments;

  public CommentResponseDto(Comment comment) {
    this.writer = comment.getUser().getNickname();
    this.content = comment.getContent();
    this.likes = comment.getLikes();
    this.reComments = comment.getReComment().stream().map(ReCommentResponseDto::new)
        .collect(Collectors.toList());
  }
}
