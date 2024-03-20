package com.example.wating.review.dto;

import java.time.LocalDateTime;

public record StoreReviewResponseDto(Long reviewId, String writerName, String reviewTitle, LocalDateTime createdAt) {

}
