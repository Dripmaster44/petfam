package com.petfam.petfam.entity;

<<<<<<< HEAD
import jakarta.persistence.Column;
=======

>>>>>>> origin/dev
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
<<<<<<< HEAD
import jakarta.persistence.OneToMany;
=======

>>>>>>> origin/dev
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CommentLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn
	@ManyToOne
	private Comment comment;

	@JoinColumn
	@ManyToOne
	private User user;

	public CommentLike(Comment comment, User user) {
		this.comment = comment;
		this.user = user;
	}
}
