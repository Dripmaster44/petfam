package com.petfam.petfam.dto.user;

import lombok.Getter;

@Getter
public class ProfileUpdateDto {
  private String nickname;
  private String introduction;
  private String image;

  public ProfileUpdateDto(String nickname, String introduction, String image) {
    this.nickname = nickname;
    this.introduction = introduction;
    this.image = image;
  }
}
