package com.petfam.petfam.dto.user;

import lombok.Getter;

@Getter
public class AdminSignupRequestDto {

  private String username;
  private String password;
  private String nickname;
  private String adminKey;
}
