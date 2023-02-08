package com.petfam.petfam.entity;



import com.petfam.petfam.dto.CommentRequestDto;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	@JoinColumn
	@ManyToOne
	private User user;

	@JoinColumn
	@ManyToOne
	private Post post;

	@Column
	private Integer likes;

	@JoinColumn
	@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReComment> reComment = new ArrayList<>();

	@Builder
	public Comment(String content, User user, Post post) {
		this.content = content;
		this.user = user;
		this.post = post;
		this.likes = 0;
	}

	public void updateLike(boolean islike) {
		likes += islike ? 1 : -1;
		if (likes < 0)
			likes = 0;
	}

	public Comment(Post post, User user, CommentRequestDto commentRequestDto) {
		this.content = commentRequestDto.getContent();
		this.user = user;
		this.post = post;
	}

	public void updateComment(String content){
		this.content = content;
	}

}
