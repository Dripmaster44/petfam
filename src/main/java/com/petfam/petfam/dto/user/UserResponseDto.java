package com.petfam.petfam.dto.user;

import com.petfam.petfam.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

  private String username;
  private String nickname;
  private String image;
  private String introduction;
  private String role;

  public UserResponseDto(User user) {
    this.username = user.getUsername();
    this.nickname = user.getNickname();
    this.image = user.getImage();
    this.introduction = user.getIntroduction();
    this.role = user.getUserRole().getAuthority();
  }
}
