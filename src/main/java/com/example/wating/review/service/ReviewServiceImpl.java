package com.example.wating.review.service;

import com.example.wating.user.entity.User;
import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.review.dao.ReviewRepository;
import com.example.wating.review.dto.ReviewRequestDto;
import com.example.wating.review.dto.ReviewResponseDto;
import com.example.wating.review.entity.Review;
import com.example.wating.review.service.interfaces.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;
  @Override
  @Transactional
  public StatusResponseDto addReview(User user, ReviewRequestDto reviewRequestDto,Long storeId) {
    Review review = Review.builder()
        .build();

    reviewRepository.save(review);

    return new StatusResponseDto(201,"Created");
  }
  @Override
  @Transactional(readOnly = true)
  public ReviewResponseDto getReview(Long reviewId) {
    Review review =  reviewRepository.findById(reviewId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );
    return new ReviewResponseDto(review.getId(), review.getReviewContent(), review.getTasteRating(),
        review.getAtmosphereRating(), review.getServiceRating(), review.getTotalRating(),review.getCreatedAt());
  }

  @Override
  public Review findReviewByReviewId(Long reviewId) {
    return reviewRepository.findById(reviewId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );
  }
}
