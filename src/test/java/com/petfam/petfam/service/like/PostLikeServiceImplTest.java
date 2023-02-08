package com.petfam.petfam.service.like;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.petfam.petfam.entity.Post;
import com.petfam.petfam.entity.PostLike;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.repository.PostRepository;
import com.petfam.petfam.repository.like.PostLikeRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostLikeServiceImplTest {

  @Mock
  private PostLikeRepository postLikeRepository;

  @InjectMocks
  private PostLikeServiceImpl postLikeService;

  @Mock
  private User user;

  @Mock
  private PostRepository postRepository;

  @Test
  @DisplayName("처음 좋아요를 누를 때")
  void likePost_First() {
    // given
    Post post = Post.builder().id(100L).title("글 제목").content("글 내용").image("글 이미지").build();
    User user = User.builder().id(200L).username("gildong").password("pass").nickname("홍길동")
        .build();
    PostLike postLike = new PostLike();
    boolean islike = false;
    when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));

    // when
    postLikeService.likePost(post.getId(), user);

    // then
    assertEquals(1, post.getPostLikes());
  }

}