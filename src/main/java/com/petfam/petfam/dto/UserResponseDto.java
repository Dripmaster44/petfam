package com.petfam.petfam.dto;

import com.petfam.petfam.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
  private String nickname;
  private String image;
  private String role;

  public UserResponseDto(User user) {
    this.nickname = user.getNickname();
    this.image = user.getImage();
    this.role = user.getUserRole().getAuthority();
  }
}
