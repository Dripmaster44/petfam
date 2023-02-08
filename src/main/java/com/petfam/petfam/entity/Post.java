package com.petfam.petfam.entity;

import com.petfam.petfam.dto.PostCreateRequestDto;
import com.petfam.petfam.dto.PostUpdateRequestDto;
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

  private Integer likes = 0;

  @JoinColumn
  @OneToMany
  private List<Comment> comments = new ArrayList<>();


  private CategoryEnum category;

  // id 생성자에 추가 -> 테스트코드를 위해서,이후 삭제 예정
  @Builder
  public Post(Long id, String title, String content, String image, User user) {
    this.id = id;
    this.user = user;
    this.title = title;
    this.content = content;
    this.image = image;
    this.likes = 0;
  }

  public Post(PostCreateRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
    this.image = requestDto.getImage();
  }
  public void update(PostUpdateRequestDto dto){
    this.title = dto.getTitle();
    this.content = dto.getContent();
    this.image = dto.getImage();
  }

  public void updateLike(boolean islike) {
    likes += islike ? 1 : -1;
    if (likes < 0) {
      likes = 0;
    }
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
}
