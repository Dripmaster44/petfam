package com.petfam.petfam.entity;

import com.petfam.petfam.dto.AllPostResponseDto;
import com.petfam.petfam.dto.CreatePostRequestDto;
import com.petfam.petfam.dto.PostRequestDto;
import com.petfam.petfam.dto.PostResponseDto;
import com.petfam.petfam.entity.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @JoinColumn
    @OneToMany
    private List<PostLike> postLikes = new ArrayList<>();

    @JoinColumn
    @OneToMany
    private List<Comment> comments = new ArrayList<>();


    private CategoryEnum category;


    public Post(CreatePostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.image = requestDto.getImage();
    }

    public void update(PostRequestDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.image = dto.getImage();
    }

}
