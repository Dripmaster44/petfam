package com.petfam.petfam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petfam.petfam.dto.AllPostResponseDto;
import com.petfam.petfam.dto.CreatePostRequestDto;
import com.petfam.petfam.dto.PostRequestDto;
import com.petfam.petfam.dto.UpdatePostResponseDto;
import com.petfam.petfam.entity.Post;
import com.petfam.petfam.entity.PostLike;
import com.petfam.petfam.repository.PostRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{

	private final PostRepository postRepository;
	private final PostLikeRepository postLikeRepository;
	private final CommentRepository commentRepository;
	private final RecommentRepositroy recommentRepositroy;


	@Override
	public String createPost(CreatePostRequestDto requestDto) {
		Post post = new Post(requestDto);
		postRepository.save(post);
		return "게시글 작성이 완료되었습니다.";
	}

	@Transactional(readOnly = true)
	@Override
	public List<AllPostResponseDto> getAllPosts() {
		List<Post> posts = postRepository.findAll();
		List<AllPostResponseDto> allPostResponseDtos = new ArrayList<>();

		for(Post post : posts){
			List<PostLike> postLikes = postLikeRepository.findByPostId(post.getId());
			allPostResponseDtos.add(new AllPostResponseDto(post,postLikes.size()));
		}
		return allPostResponseDtos;
	}
	/*@Transactional(readOnly = true)
	@Override
	public PostResponseDto getSelectPost(Long postId) {
		Post post = _findPost(postId);
		List<PostLike> postLikes = postLikeRepository.findByPostId(postId);
		List<Comment> comments = commentRepository.findByPostId(postId);

		for(Comment comment : comments){
			List<ReComment> reComments = recommentRepositroy.findByCommentId(comment.getId());

		}

		return new PostResponseDto(post,postLikes.size(),comments);
	}*/

	@Override
	public UpdatePostResponseDto updatePost(Long postId, PostRequestDto requestDto) {
		Post post = _findPost(postId);
		post.update(requestDto);
		return new UpdatePostResponseDto(post);
	}


	@Override
	public String deletePost(Long postId) {
		Post post = _findPost(postId);
		postRepository.delete(post);
		return "게시글이 삭제되었습니다.";
	}

	// 중복 코드
	private Post _findPost(Long postId){
		Post post = postRepository.findById(postId).orElseThrow(
			()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
		);
		return post;
	}
}
