package com.petfam.petfam.repository;

import com.petfam.petfam.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Long findBypostId(Long postId);
}
