package com.petfam.petfam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petfam.petfam.dto.AllPostResponseDto;
import com.petfam.petfam.dto.PostCreateRequestDto;
import com.petfam.petfam.dto.PostUpdateRequestDto;
import com.petfam.petfam.dto.PostResponseDto;
import com.petfam.petfam.dto.PostUpdateResponseDto;
import com.petfam.petfam.entity.Comment;
import com.petfam.petfam.entity.Post;
import com.petfam.petfam.repository.CommentRepository;
import com.petfam.petfam.repository.ReCommentRepository;
import com.petfam.petfam.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final ReCommentRepository reCommentRepository;


	@Override
	public String createPost(PostCreateRequestDto requestDto) {
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
			allPostResponseDtos.add(new AllPostResponseDto(post));
		}
		return allPostResponseDtos;
	}

	@Transactional(readOnly = true)
	@Override
	public PostResponseDto getSelectPost(Long postId) {
		Post post = _findPost(postId);
		List<Comment> comments = commentRepository.findAllByPost(post);
		for(Comment comment : comments){
			comment.setReComment(reCommentRepository.findAllByComment(comment));
		}
		post.setComments(comments);
		return new PostResponseDto(post);
	}

	@Override
	public PostUpdateResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto) {
		Post post = _findPost(postId);
		post.update(requestDto);
		return new PostUpdateResponseDto(post);
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
