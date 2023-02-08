package com.petfam.petfam.service;

import com.petfam.petfam.dto.CommentRequestDto;
import com.petfam.petfam.entity.Comment;
import com.petfam.petfam.entity.Post;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.repository.CommentRepository;
import com.petfam.petfam.repository.PostRepository;
import com.petfam.petfam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    // 댓글 생성
    @Transactional
    public String comment(Long postId, String username, CommentRequestDto commentRequestDto){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        Comment comment = new Comment(post,user,commentRequestDto);
        return "댓글 생성이 완료되었습니다.";
    }

    // 댓글 수정
    @Transactional
    public String updateComment(Long commentId, Long postId, String username, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        if (!user.isAdmin()) {
            if (comment.getUser().getUsername().equals(username)) {
                comment.updateComment(commentRequestDto.getContent());
            } else throw new IllegalArgumentException("자신이 작성한 댓글만 수정이 가능합니다.");
        }
        return "댓글 생성 완료";
    }

    // 댓글 삭제
    @Transactional
    public String deleteComment(Long commentId, Long postId, String username, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        if (!user.isAdmin()) {
            if (comment.getUser().getUsername().equals(username)) {
                commentRepository.deleteById(commentId);
            } else throw new IllegalArgumentException("본인이 작성한 댓글만 삭제가 가능합니다.");
        }
        return "삭제 완료";
    }

}
