package com.petfam.petfam.dto.post;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.petfam.petfam.dto.CategoryDto;
import com.petfam.petfam.dto.comment.CommentResponseDto;
import com.petfam.petfam.entity.Post;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = PostResponseDto.PostResponseDtoBuilder.class)
public class PostResponseDto {

  private Long id;
  private String title;
  private String image;
  private String writer;
  private Long writerId;
  private Integer likes;
  private String content;
  private CategoryDto category;
  private List<CommentResponseDto> comments; // 리스트

  public PostResponseDto(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.image = post.getImage();
    this.content = post.getContent();
    this.writer = post.getUser().getNickname();
    this.writerId = post.getUser().getId();
    this.likes = post.getLikes();
    this.category = new CategoryDto(post.getCategory());
    this.comments = post.getComments().stream().map(CommentResponseDto::new)
        .collect(Collectors.toList());
  }

  public static class PostResponseDtoBuilder {

    @JsonSetter("id")
    private Long id;
    @JsonSetter("title")
    private String title;
    @JsonSetter("image")
    private String image;
    @JsonSetter("writer")
    private String writer;
    @JsonSetter("writerId")
    private Long writerId;
    @JsonSetter("likes")
    private Integer likes;
    @JsonSetter("content")
    private String content;
    @JsonSetter("category")
    private CategoryDto category;
    @JsonSetter("comments")
    private List<CommentResponseDto> comments;

    public PostResponseDto build(Post post) {
      return PostResponseDto.builder()
          .id(post.getId())
          .title(post.getTitle())
          .image(post.getImage())
          .writer(post.getUser().getNickname())
          .writerId(post.getUser().getId())
          .likes(post.getLikes())
          .content(post.getContent())
          .category(new CategoryDto(post.getCategory()))
          .comments(post.getComments().stream()
              .map(CommentResponseDto::new)
              .collect(Collectors.toList()))
          .build(post);
    }
  }
}

