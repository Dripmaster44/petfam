package com.petfam.petfam.dto.user;

import com.petfam.petfam.entity.User;
import lombok.Getter;

@Getter
public class ProfileResponseDto {

  private String nickname;
  private String introduction;
  private String image;
  private String role;
  public ProfileResponseDto(User user) {
    this.nickname = user.getNickname();
    this.introduction = user.getIntroduction();
    this.image = user.getImage();
    this.role = user.getUserRole().getAuthority();
  }
}
