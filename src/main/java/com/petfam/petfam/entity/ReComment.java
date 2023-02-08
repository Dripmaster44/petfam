package com.petfam.petfam.entity;

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


  @Builder
  public ReComment(String content, User user, Comment comment) {
    this.content = content;
    this.user = user;
    this.comment = comment;
  }


  @JoinColumn
  @ManyToOne
  private User user;

  @JoinColumn
  @ManyToOne
  private Comment comment;

//  @JoinColumn
//  @OneToMany(mappedBy = "ReComment",cascade = CascadeType.ALL,orphanRemoval = true)
//  private List<ReCommentLike> reCommentLikes = new ArrayList<>();
  
}
