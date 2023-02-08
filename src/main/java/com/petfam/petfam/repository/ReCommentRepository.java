package com.petfam.petfam.repository;

import java.util.List;

import com.petfam.petfam.entity.Comment;
import com.petfam.petfam.entity.ReComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReCommentRepository extends JpaRepository<ReComment, Long> {

    Long findByCommentId(Long commentId);


}
