package com.petfam.petfam.dto.user;

import lombok.Getter;

@Getter
public class AdminSigninRequestDto {

  private String username;

  private String password;

  private String adminKey;

}
