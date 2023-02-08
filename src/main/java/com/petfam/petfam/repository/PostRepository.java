package com.petfam.petfam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.petfam.petfam.entity.Post;


public interface PostRepository extends JpaRepository<Post,Long> {

}
