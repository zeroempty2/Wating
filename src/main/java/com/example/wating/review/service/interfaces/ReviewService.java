package com.example.wating.review.service.interfaces;


import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.review.dto.ReviewRequestDto;
import com.example.wating.review.dto.ReviewResponseDto;
import com.example.wating.review.dto.StoreReviewResponseDto;
import com.example.wating.review.entity.Review;
import com.example.wating.user.entity.User;
import java.util.List;

public interface ReviewService {
  StatusResponseDto addReview(User user, ReviewRequestDto reviewRequestDto,Long storeId);
  //단일 리뷰 불러오기
  List<StoreReviewResponseDto> getStoreReviews(Long storeId);
  ReviewResponseDto getReview(Long reviewId);
  Review findReviewByReviewId(Long reviewId);
}
