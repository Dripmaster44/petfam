package com.petfam.petfam.dto.post;

import com.petfam.petfam.entity.enums.CategoryEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostCreateRequestDto {

  private String title;
  private String content;
  private String image;
  private CategoryEnum category;

}
