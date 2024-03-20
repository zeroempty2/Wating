package com.example.wating.review.dao;

import com.example.wating.review.dto.StoreReviewResponseDto;
import java.util.List;

public interface ReviewRepositoryQuery {
  List<StoreReviewResponseDto> findReviewListByStoreId(Long storeId);
}
