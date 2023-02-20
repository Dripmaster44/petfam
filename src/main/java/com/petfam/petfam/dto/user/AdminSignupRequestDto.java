package com.petfam.petfam.dto.user;

import lombok.Getter;

@Getter
public class AdminSignupRequestDto {

  private String username;
  private String password;
  private String nickname;
  private String adminKey;

  public AdminSignupRequestDto(String username, String password, String nickname, String adminKey) {
    this.username = username;
    this.password = password;
    this.nickname = nickname;
    this.adminKey = adminKey;
  }
}
