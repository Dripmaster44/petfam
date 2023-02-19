package com.petfam.petfam.service.post;

import com.petfam.petfam.dto.post.AllPostResponseDto;
import com.petfam.petfam.dto.post.PostCreateRequestDto;
import com.petfam.petfam.dto.post.PostResponseDto;
import com.petfam.petfam.dto.post.PostUpdateRequestDto;
import com.petfam.petfam.dto.post.PostUpdateResponseDto;
import com.petfam.petfam.entity.Post;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.entity.enums.CategoryEnum;
import com.petfam.petfam.entity.enums.UserRoleEnum;
import com.petfam.petfam.repository.PostRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;


  @Override
  @Transactional
  public String createPost(PostCreateRequestDto requestDto, User user) {
    Post post = new Post(requestDto, user);
    postRepository.save(post);
    return "게시글 작성이 완료되었습니다.";
  }

  //  @Transactional(readOnly = true)
//  @Override
//  public List<AllPostResponseDto> getAllPosts() {
//    List<Post> posts = postRepository.findAll();
//    List<AllPostResponseDto> allPostResponseDtos = new ArrayList<>();
//
//    for (Post post : posts) {
//      allPostResponseDtos.add(new AllPostResponseDto(post));
//    }
//    return allPostResponseDtos;
//  }
  @Transactional(readOnly = true)
  @Override
  public Page<AllPostResponseDto> getAllPosts(Pageable pageable) {
    Page<Post> posts = postRepository.findAll(pageable);
    List<AllPostResponseDto> allPostResponseDtoList = new ArrayList<>();

    for (Post post : posts) {
      AllPostResponseDto allPostResponseDto = new AllPostResponseDto(post);
      allPostResponseDtoList.add(allPostResponseDto);
    }
    return new PageImpl<>(allPostResponseDtoList, pageable, posts.getTotalElements());
  }

  @Transactional(readOnly = true)
  @Override
  public Page<AllPostResponseDto> getPostsByCategory(CategoryEnum category, Pageable pageable) {
    Page<Post> posts = postRepository.findByCategoryOrderByCreatedAtDesc(category, pageable);

    List<AllPostResponseDto> allPostResponseDtoList = new ArrayList<>();

    for (Post post : posts) {
      AllPostResponseDto allPostResponseDto = new AllPostResponseDto(post);
      allPostResponseDtoList.add(allPostResponseDto);
    }
    return new PageImpl<>(allPostResponseDtoList, pageable, posts.getTotalElements());
  }

  @Transactional(readOnly = true)
  @Override
  public PostResponseDto getSelectPost(Long postId) {
    Post post = _findPost(postId);
    return new PostResponseDto(post);
  }

  @Transactional
  @Override
  public PostUpdateResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto, User user) {
    Post post = _findPost(postId);

    // exception처리 후 살릴 부분
    // admin과 글 작성자만 수정할 수 있는 기능
    if (user.getUserRole() != UserRoleEnum.ADMIN) {
      if (!post.getUser().getId().equals(user.getId())) {
        throw new IllegalArgumentException("글 작성자만 수정이 가능합니다.");
      }
    }

    post.updatePost(requestDto);
    return new PostUpdateResponseDto(post);
  }


  @Transactional
  @Override
  public String deletePost(Long postId, User user) {
    Post post = _findPost(postId);

    if (user.getUserRole() != UserRoleEnum.ADMIN) {
      if (!post.getUser().getId().equals(user.getId())) {
        throw new IllegalArgumentException("글 작성자만 수정이 가능합니다.");
      }
    }

    postRepository.deleteById(postId);
    return "게시글이 삭제되었습니다.";
  }

  //좋아요 상위3개 출력
  @Override
  public List<PostResponseDto> getTopThreePosts() {
    List<Post> allPosts = postRepository.findAll();
    List<PostResponseDto> postResponseDtoList = new ArrayList<>();

    for(Post post : allPosts){
      PostResponseDto postResponseDto = new PostResponseDto(post);
      postResponseDtoList.add(postResponseDto);
    }
    Collections.sort(postResponseDtoList, new Comparator<PostResponseDto>() {
      @Override
      public int compare(PostResponseDto post1, PostResponseDto post2) {
        return post2.getLikes() - post1.getLikes();
      }
    });
    return postResponseDtoList.subList(0, Math.min(3, postResponseDtoList.size()));
  }


  // 중복 코드
  private Post _findPost(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
    );
    return post;
  }
}
