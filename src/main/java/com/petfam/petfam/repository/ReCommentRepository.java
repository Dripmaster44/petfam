package com.petfam.petfam.repository;

import com.petfam.petfam.entity.ReComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReCommentRepository extends JpaRepository<ReComment, Long> {

    Long findByCommentId(Long commentId);


}
