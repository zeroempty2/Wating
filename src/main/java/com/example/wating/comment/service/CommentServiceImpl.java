package com.example.wating.comment.service;

import com.example.wating.comment.dao.CommentRepository;
import com.example.wating.comment.dto.CommentRequestDto;
import com.example.wating.comment.dto.CommentResponseDto;
import com.example.wating.comment.entity.Comment;
import com.example.wating.comment.service.interfaces.CommentService;
import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.review.dao.ReviewRepository;
import com.example.wating.review.entity.Review;
import com.example.wating.user.entity.User;
import com.example.wating.user.service.interfaces.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
  private CommentRepository commentRepository;
  private UserService userService;
  private ReviewRepository reviewRepository;
  @Override
  @Transactional
  public StatusResponseDto addComment(User user, CommentRequestDto commentRequestDto,Long reviewId) {
    Review review = reviewRepository.findById(reviewId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
    );
    Comment comment = Comment.builder()
        .userId(user.getId())
        .commentContent(commentRequestDto.commentContents())
        .review(review)
        .build();
    commentRepository.save(comment);
    return new StatusResponseDto(200,"OK");
  }

  @Override
  @Transactional(readOnly = true)
  public CommentResponseDto getComment(Long commentId) {
    return commentRepository.responseCommentDtoByCommentId(commentId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
    );
  }

  @Override
  @Transactional(readOnly = true)
  public List<CommentResponseDto> getReviewComments(Long reviewId) {
    return commentRepository.findCommentsByReviewId(reviewId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<CommentResponseDto> getUserComments(Long userId) {
    return commentRepository.findCommentsByUserId(userId);
  }

  @Override
  @Transactional
  public StatusResponseDto updateComment(User user, Long commentId,
      CommentRequestDto commentRequestDto) {
    Comment comment = findCommentByCommentId(commentId);

    if(!comment.isWriter(user,comment)) return new StatusResponseDto(400,"Bad Request");

    comment.update(commentRequestDto);

    return new StatusResponseDto(200,"OK");
  }

  @Override
  @Transactional
  public StatusResponseDto deleteComment(User user, Long commentId) {
    Comment comment = findCommentByCommentId(commentId);

    if(!comment.isWriter(user,comment)) return new StatusResponseDto(400,"Bad Request");

    commentRepository.deleteById(commentId);

    return new StatusResponseDto(204,"NO_CONTENT");
  }

  @Override
  public Comment findCommentByCommentId(Long commentId) {
    return  commentRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );
  }



}
