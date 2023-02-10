package com.petfam.petfam.dto.post;

import com.petfam.petfam.dto.comment.CommentResponseDto;
import com.petfam.petfam.entity.Post;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class PostResponseDto {

  private String title;
  private String image;
  private String writer;
  private Long writerId;
  private Integer likes;
  private String content;
  private List<CommentResponseDto> comments; // 리스트

  public PostResponseDto(Post post) {
    this.title = post.getTitle();
    this.image = post.getImage();
    this.content = post.getContent();
    this.writer = post.getUser().getNickname();
    this.writerId = post.getUser().getId();
    this.likes = post.getLikes();
    this.comments = post.getComments().stream().map(CommentResponseDto::new)
        .collect(Collectors.toList());
  }
}

