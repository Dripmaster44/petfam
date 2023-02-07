package com.petfam.petfam.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@JoinColumn
	@OneToMany
	private List<ReComment> reComment = new ArrayList<>();

	@JoinColumn
	@OneToMany
	private List<CommentLike> commentLike = new ArrayList<>();

	@Builder
	public Comment(String content, User user, Post post) {
		this.content = content;
		this.user = user;
		this.post = post;
	}
}
