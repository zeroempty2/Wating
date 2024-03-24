package com.example.wating.review.dao;

import com.example.wating.review.dto.ReviewResponseDto;
import com.example.wating.review.dto.StoreReviewResponseDto;
import com.example.wating.review.entity.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryQuery {
  List<StoreReviewResponseDto> findReviewListByStoreId(Long storeId);
  ReviewResponseDto getReviewByReviewId(Long reviewId);
}
