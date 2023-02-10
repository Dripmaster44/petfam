package com.petfam.petfam.dto.post;

import com.petfam.petfam.dto.comment.CommentResponseDto;
import com.petfam.petfam.entity.Comment;
import com.petfam.petfam.entity.Post;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class PostResponseDto {

  private String title;
  private String image;
  private String writer;
  private Long writerId;
  private Integer likes;
  private String content;
  private List<CommentResponseDto> comments= new ArrayList<>(); // 리스트

  public PostResponseDto(Post post) {
    this.title = post.getTitle();
    this.image = post.getImage();
    this.content = post.getContent();
    this.writer = post.getUser().getNickname();
    this.writerId = post.getUser().getId();
    this.likes = post.getLikes();

    List<Comment> comments = post.getComments();
    for (Comment comment : comments) {
      this.comments.add(new CommentResponseDto(comment));
    }
  }
}
