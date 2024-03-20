package com.example.wating.like.service.interfaces;

import com.example.wating.common.dto.StatusResponseDto;

public interface ReviewLikeService {
  StatusResponseDto requestReviewLike(Long userId, Long reviewId);
}
