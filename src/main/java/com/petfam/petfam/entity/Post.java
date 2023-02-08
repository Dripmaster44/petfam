package com.petfam.petfam.entity;

import com.petfam.petfam.entity.enums.CategoryEnum;
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

@Getter
@Entity
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn
  @ManyToOne
  private User user;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column
  private String image;

  private Integer postLikes = 0;

  @JoinColumn
  @OneToMany
  private List<Comment> comments = new ArrayList<>();


  private CategoryEnum category;

  @Builder
  public Post(String title, String content, String image) {
    this.title = title;
    this.content = content;
    this.image = image;
  }

  public void updateLike(boolean islike) {
    postLikes += islike ? 1 : -1;
    if (postLikes < 0) {
      postLikes = 0;
    }
  }
}
