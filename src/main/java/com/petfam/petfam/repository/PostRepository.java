package com.petfam.petfam.repository;

import com.petfam.petfam.entity.Post;
import com.petfam.petfam.entity.enums.CategoryEnum;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  Page<Post> findByCategoryOrderByCreatedAtDesc(CategoryEnum category, Pageable pageable);
}
