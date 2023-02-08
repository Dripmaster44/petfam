package com.petfam.petfam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ReComment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String content;

  @Column
  private Integer likes;


  @Builder
  public ReComment(String content, User user, Comment comment) {
    this.content = content;
    this.user = user;
    this.comment = comment;
    this.likes = 0;
  }


  @ManyToOne
  @JoinColumn
  private User user;

  @ManyToOne
  @JoinColumn
  private Comment comment;

  public void updateLike(boolean islike) {
    likes += islike ? 1 : -1;
    if (likes < 0)
      likes = 0;
  }


}
