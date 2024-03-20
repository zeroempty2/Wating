package com.example.wating.like.dao.reviewLike;

import com.example.wating.like.entity.reviewLike.ReviewLike;
import com.example.wating.like.entity.reviewLike.ReviewLikeId;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReviewLikeRepository extends Repository<ReviewLike, ReviewLikeId>,ReviewLikeRepositoryQuery {
  void save(ReviewLike reviewLike);
  Optional<ReviewLike> findByReviewIdAndUserId(Long reviewId, Long userId);
  void deleteByReviewLikeId(ReviewLikeId reviewLikeId);
}

