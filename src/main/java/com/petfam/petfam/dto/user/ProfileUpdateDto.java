package com.petfam.petfam.dto.user;

import lombok.Getter;

@Getter
public class ProfileUpdateDto {
  private String nickname;
  private String introduction;
  private String image = "src/main/java/resources/static/images/m_20220509173224_d9N4ZGtBVR.jpeg";

}
