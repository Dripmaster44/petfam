package com.petfam.petfam.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public Post(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
