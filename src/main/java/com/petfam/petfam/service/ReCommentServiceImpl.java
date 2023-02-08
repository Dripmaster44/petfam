package com.petfam.petfam.service;

import com.petfam.petfam.dto.ReCommentRequestDto;
import com.petfam.petfam.entity.Comment;
import com.petfam.petfam.entity.ReComment;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.repository.CommentRepository;
import com.petfam.petfam.repository.ReCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReCommentServiceImpl implements ReCommentService {
    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    private final ReCommentRepository reCommentRepository;

    // 대댓글 생성
    @Transactional
    public String reComment(Long commentId, String username, ReCommentRequestDto reCommentRequestDto) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        ReComment reComment = new ReComment(comment, user, reCommentRequestDto);
        return "댓글 생성이 완료되었습니다.";
    }

    // 대댓글 수정
    @Transactional
    public String updateReComment(Long commentId, Long reCommentId, String username, ReCommentRequestDto reCommentRequestDto) {
        ReComment reComment = reCommentRepository.findById(reCommentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        if (!user.isAdmin()) {
            if (reComment.getUser().getUsername().equals(username)) {
                reComment.updateReComment(reCommentRequestDto.getContent());
            } else throw new IllegalArgumentException("자신이 작성한 댓글만 수정이 가능합니다.");
        }
        return "댓글 수정 완료";
    }

    // 대댓글 삭제
    @Transactional
    public String deleteReComment(Long reCommentId, Long commentId, String username, ReCommentRequestDto reCommentRequestDto) {
        ReComment reComment = reCommentRepository.findById(reCommentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        if (!user.isAdmin()) {
            if (reComment.getUser().getUsername().equals(username)) {
                reCommentRepository.deleteById(reCommentId);
            } else throw new IllegalArgumentException("자신이 작성한 댓글만 삭제가 가능합니다.");
        }
        return "댓글 삭제가 완료되었습니다.";
    }


}
