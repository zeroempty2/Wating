package com.example.wating.like.service.interfaces;

import com.example.wating.common.dto.StatusResponseDto;

public interface CommentLikeService {
  StatusResponseDto requestCommentLike(Long userId, Long commentId);
}
