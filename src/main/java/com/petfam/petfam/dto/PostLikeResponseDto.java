package com.petfam.petfam.dto;

import lombok.Getter;

@Getter
public class PostLikeResponseDto {

  private final String msg;

  private final int statuscode;

  public PostLikeResponseDto(String msg, int statuscode) {
    this.msg = msg;
    this.statuscode = statuscode;
  }
}
