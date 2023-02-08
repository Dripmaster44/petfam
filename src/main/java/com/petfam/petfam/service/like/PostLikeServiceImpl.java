package com.petfam.petfam.service.like;

import com.petfam.petfam.dto.PostLikeResponseDto;
import com.petfam.petfam.entity.Post;
import com.petfam.petfam.entity.PostLike;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.repository.PostRepository;
import com.petfam.petfam.repository.like.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

  private final PostRepository postRepository;

  private final PostLikeRepository postLikeRepository;

  @Override
  @Transactional
  public PostLikeResponseDto likePost(Long postId, User user) {
    boolean islike = false;

    PostLike likelog = postLikeRepository.findByUser_IdAndTargetId(user.getId(), postId)
        .orElse(null);

    String msg = "";

    if (likelog == null) {
      likelog = new PostLike(user, postId);
      islike = true;
      postLikeRepository.save(likelog);
      msg = "좋아요를 누르셨습니다.";
    } else {
      islike = false;
      postLikeRepository.save(likelog);
      msg = "좋아요를 취소하셨습니다.";
    }

    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

    post.updateLike(islike);

    return new PostLikeResponseDto(msg, 200);
  }

}
