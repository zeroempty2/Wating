package com.example.wating.review.dto;

import java.time.LocalDateTime;

public record ReviewResponseDto(Long reviewId, String reviewContent, Float tasteRating, Float atmosphereRating, Float serviceRating, Float totalRating, LocalDateTime createdAt) {

}
