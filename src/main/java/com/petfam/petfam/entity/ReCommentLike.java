package com.petfam.petfam.entity;

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
public class ReCommentLike {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @Builder
  public ReCommentLike(User user, ReComment reComment) {
    this.user = user;
    this.reComment = reComment;
  }


  @ManyToOne
  @JoinColumn
  private User user;

  @ManyToOne
  @JoinColumn
  private ReComment reComment;



}
