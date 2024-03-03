package com.example.wating.comment.service.interfaces;

import com.example.wating.comment.dto.CommentRequestDto;
import com.example.wating.comment.dto.CommentResponseDto;
import com.example.wating.comment.entity.Comment;
import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.user.entity.User;
import java.util.List;

public interface CommentService {
  StatusResponseDto addComment(User user, CommentRequestDto commentRequestDto,Long reviewId);
  //단일 댓글 불러오기
  CommentResponseDto getComment(Long commentId);
  //리뷰 댓글 리스트 불러오기
  List<CommentResponseDto> getReviewComments(Long reviewId);
  //유저의 댓글 리스트 가져오기
  List<CommentResponseDto> getUserComments(Long commentId);
  //댓글 수정하기
  StatusResponseDto updateComment(User user, Long commentId, CommentRequestDto commentRequestDto);
  //댓글 삭제하기
  StatusResponseDto deleteComment(User user, Long commentId);

  Comment findCommentByCommentId(Long commentId);


}
