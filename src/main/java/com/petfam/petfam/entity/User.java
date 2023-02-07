package com.petfam.petfam.entity;

import com.petfam.petfam.entity.enums.UserRoleEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// lombok
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

//jpa
@Entity(name = "users")
public class User {

  /**
   * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String nickname;

  private String image;

  private String introduction;

  private Integer point;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserRoleEnum userRole;



  /**
   * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
   */
  @Builder
  public User(String username, String password, String nickname,
      String image, UserRoleEnum userRole ){
    this.username = username;
    this.password = password;
    this.nickname = nickname;
    this.image = "src/main/java/resources/static/images/m_20220509173224_d9N4ZGtBVR.jpeg";
    this.introduction = "안녕하세요.";
    this.point = 0;
    this.userRole = userRole;
  }


  /**
   * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
   */
//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//  private final List<Post> posts = new ArrayList<>();
//
//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//  private final List<Comment> comments = new ArrayList<>();
//
//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//  private final List<ReComment> reComments = new ArrayList<>();


  /**
   * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
   */



  /**
   * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
   */
  // admin 검증 함수
  public boolean isAdmin(){
    return this.userRole == UserRoleEnum.ADMIN;
  }

  // 닉네임 업데이트
  public void updateNickname(String nickname){
    this.nickname = nickname;
  }
}
