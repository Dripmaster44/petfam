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

@Getter
@Entity
@NoArgsConstructor
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne
    private Post post;

    @JoinColumn
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Long targetId;

    @Builder
    public PostLike(User user, Long targetId) {
        this.user = user;
        this.targetId = targetId;
    }

}
