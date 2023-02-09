package com.petfam.petfam.entity;


import com.petfam.petfam.dto.comment.CommentRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String content;

  @JoinColumn
  @ManyToOne
  private User user;

  @JoinColumn
  @ManyToOne
  private Post post;

  @Column
  private Integer likes;

  @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ReComment> reComment = new ArrayList<>();


  public void updateLike(boolean islike) {
    likes += islike ? 1 : -1;
    if (likes < 0) {
      likes = 0;
    }
  }

  public Comment(Post post, User user, CommentRequestDto commentRequestDto) {
    this.content = commentRequestDto.getContent();
    this.user = user;
    this.post = post;
    this.likes = 0;
  }

  public void updateComment(String content) {
    this.content = content;
  }

  public void setReComment(List<ReComment> reComment) {
    this.reComment = reComment;
  }

}
