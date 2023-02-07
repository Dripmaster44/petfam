package com.petfam.petfam.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private List<PostLike> postLike = new ArrayList<>();

    @JoinColumn
    @OneToMany
    private List<Comment> comment = new ArrayList<>();


    private CategoryEnum category;

    public Post(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
