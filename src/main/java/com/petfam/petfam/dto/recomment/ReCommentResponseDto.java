package com.petfam.petfam.dto.recomment;

import com.petfam.petfam.entity.ReComment;
import lombok.Getter;

@Getter
public class ReCommentResponseDto {

  private String writer;
  private String conetent;

  public ReCommentResponseDto(ReComment recomment) {
    this.writer = recomment.getUser().getNickname();
    this.conetent = recomment.getContent();
  }
}
