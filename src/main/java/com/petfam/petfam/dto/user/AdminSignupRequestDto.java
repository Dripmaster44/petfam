package com.petfam.petfam.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminSignupRequestDto {

  private String username;
  private String password;
  private String nickname;
  private String adminKey;
}
