package com.petfam.petfam.service.like;

import com.petfam.petfam.dto.PostLikeResponseDto;
import com.petfam.petfam.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface PostLikeService {

  @Transactional
  public PostLikeResponseDto likePost(Long postId, User user);
}
