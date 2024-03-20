package com.example.wating.like.service;

import com.example.wating.comment.entity.Comment;
import com.example.wating.comment.service.interfaces.CommentService;
import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.like.dao.commentLike.CommentLikeRepository;
import com.example.wating.like.entity.commentLike.CommentLike;
import com.example.wating.like.entity.commentLike.CommentLikeId;
import com.example.wating.like.service.interfaces.CommentLikeService;
import com.example.wating.user.entity.User;
import com.example.wating.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {
  private final CommentLikeRepository commentLikeRepository;
  private final UserService userService;
  private final CommentService commentService;
  @Override
  @Transactional
  public StatusResponseDto requestCommentLike(Long userId, Long commentId) {
    CommentLikeId commentLikeId = CommentLikeId.builder()
        .userId(userId)
        .commentId(commentId)
        .build();

    if(commentLikeRepository.existByCommentLikeId(commentLikeId)){
      commentLikeRepository.deleteByCommentLikeId(commentLikeId);
    }

    else{
      User user = userService.findUserByUserId(userId);
      Comment comment = commentService.findCommentByCommentId(commentId);
      commentLikeRepository.save(new CommentLike(user,comment));
    }

    return new StatusResponseDto(200,"OK");
  }
}
