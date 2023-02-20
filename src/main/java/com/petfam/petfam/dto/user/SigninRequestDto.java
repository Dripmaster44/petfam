package com.petfam.petfam.dto.user;

import lombok.Getter;

@Getter
public class SigninRequestDto {

  private String username;
  private String password;

  public SigninRequestDto(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
