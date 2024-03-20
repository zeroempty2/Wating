package com.example.wating.like.dao.reviewLike;

import com.example.wating.like.entity.reviewLike.ReviewLikeId;

public interface ReviewLikeRepositoryQuery {
  boolean existByReviewLikeId(ReviewLikeId reviewLikeId);
}

