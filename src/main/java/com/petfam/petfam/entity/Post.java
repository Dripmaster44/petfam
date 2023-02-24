package com.petfam.petfam.entity;

import com.petfam.petfam.dto.post.PostCreateRequestDto;
import com.petfam.petfam.dto.post.PostUpdateRequestDto;
import com.petfam.petfam.entity.enums.CategoryEnum;
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

@Getter
@Entity
@NoArgsConstructor
public class Post extends TimeStamped {

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

  private Integer likes = 0;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  private CategoryEnum category;

  // 조회수
  @Column(columnDefinition = "integer default 0", nullable = false)
  private int view;


  @Builder
  public Post(PostCreateRequestDto requestDto, User user) {
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
    this.image = requestDto.getImage();
    this.user = user;
    this.likes = 0;
    this.category = requestDto.getCategory();
  }

  public void updatePost(PostUpdateRequestDto dto) {
    this.title = (dto.getTitle().equals("")) ? this.title : dto.getTitle();
    this.content = (dto.getContent().equals("")) ? this.content : dto.getContent();
    this.image = (dto.getImage().equals("")) ? this.image : dto.getImage();
    this.category = dto.getCategory().equals(this.category) ? this.category : dto.getCategory();
  }

  public void updateLike(boolean islike) {
    likes += islike ? 1 : -1;
    if (likes < 0) {
      likes = 0;
    }
  }
}
