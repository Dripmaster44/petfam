package com.petfam.petfam.repository.like;

import com.petfam.petfam.entity.PostLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

  Optional<PostLike> findByUser_IdAndTargetId(Long userId, Long targetId);
}
