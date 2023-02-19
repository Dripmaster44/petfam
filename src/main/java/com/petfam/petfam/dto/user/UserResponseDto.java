package com.petfam.petfam.dto.user;

import com.petfam.petfam.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

  private Long id;
  private String username;
  private String nickname;
  private String role;

  public UserResponseDto(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.nickname = user.getNickname();
    this.role = user.getUserRole().getAuthority();
  }
}
