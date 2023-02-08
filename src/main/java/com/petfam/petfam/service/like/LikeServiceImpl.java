package com.petfam.petfam.service.like;

import com.petfam.petfam.dto.PostLikeResponseDto;
import com.petfam.petfam.entity.Likes;
import com.petfam.petfam.entity.Post;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.entity.enums.LikeEnum;
import com.petfam.petfam.repository.LikeRepository;
import com.petfam.petfam.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

  private final PostRepository postRepository;

  private final LikeRepository likeRepository;

  @Override
  @Transactional
  public PostLikeResponseDto likePost(Long postId, User user) {
    boolean islike = false;
    String msg = "";

    Likes likelog = likeRepository.findByTypeAndUser_IdAndTargetId(LikeEnum.POST, user.getId(),
        postId).orElse(null);

    if (likelog == null) {
      likelog = new Likes(user, postId, LikeEnum.POST);
      islike = true;
      likeRepository.save(likelog);
      msg = "좋아요를 누르셨습니다.";
    } else {
      islike = false;
      likeRepository.delete(likelog);
      msg = "좋아요를 취소하셨습니다.";
    }

    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

    post.updateLike(islike);

    return new PostLikeResponseDto(msg, 200);
  }

}
