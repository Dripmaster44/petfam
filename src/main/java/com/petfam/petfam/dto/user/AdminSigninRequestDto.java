package com.petfam.petfam.dto.user;

import lombok.Getter;

@Getter
public class AdminSigninRequestDto {

  private String username;

  private String password;

  private String adminKey;

  public AdminSigninRequestDto(String username, String password, String adminKey) {
    this.username = username;
    this.password = password;
    this.adminKey = adminKey;
  }
}
