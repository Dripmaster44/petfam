package com.petfam.petfam.dto.post;

import com.petfam.petfam.entity.Post;

public class AllPostResponseDto {

  private Long id;
  private String writer;
  private String title;
  private String image;
  private Integer likes;

  public AllPostResponseDto(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.image = post.getImage();
    this.writer = post.getUser().getNickname();
    this.likes = post.getLikes();
  }

}
