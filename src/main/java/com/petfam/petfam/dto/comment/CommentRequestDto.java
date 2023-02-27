package com.petfam.petfam.dto.comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentRequestDto {

  private String content;

}
