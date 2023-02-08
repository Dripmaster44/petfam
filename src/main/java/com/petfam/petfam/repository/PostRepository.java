package com.petfam.petfam.repository;

import com.petfam.petfam.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
