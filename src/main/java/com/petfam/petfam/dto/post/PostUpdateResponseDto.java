package com.petfam.petfam.dto.post;


import com.petfam.petfam.entity.Post;

public class PostUpdateResponseDto {

  private String title;
  private String content;
  private String image;

  public PostUpdateResponseDto(Post post) {
    this.title = post.getTitle();
    this.content = post.getContent();
    this.image = post.getImage();
  }
}
