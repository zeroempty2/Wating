package com.example.wating.like.service;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.like.dao.reviewLike.ReviewLikeRepository;
import com.example.wating.like.entity.reviewLike.ReviewLike;
import com.example.wating.like.entity.reviewLike.ReviewLikeId;
import com.example.wating.like.service.interfaces.ReviewLikeService;
import com.example.wating.review.entity.Review;
import com.example.wating.review.service.interfaces.ReviewService;
import com.example.wating.user.entity.User;
import com.example.wating.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewLikeServiceImpl implements ReviewLikeService {
  private final ReviewLikeRepository reviewLikeRepository;
  private final UserService userService;
  private final ReviewService reviewService;

  @Override
  @Transactional
  public StatusResponseDto requestReviewLike(Long userId, Long reviewId) {
    ReviewLikeId reviewLikeId = ReviewLikeId.builder()
        .userId(userId)
        .reviewId(reviewId)
        .build();

    if(reviewLikeRepository.existByReviewLikeId(reviewLikeId)) reviewLikeRepository.deleteByReviewLikeId(reviewLikeId);

    else{
      User user = userService.findUserByUserId(userId);
      Review review = reviewService.findReviewByReviewId(reviewId);
      reviewLikeRepository.save(new ReviewLike(user,review));
    }

    return new StatusResponseDto(200,"OK");
  }
}
