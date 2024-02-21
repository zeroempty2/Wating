package com.example.wating.review.dto;



public record ReviewRequestDto(Long reservationId, String reviewContent, Float tasteRating, Float atmosphereRating, Float serviceRating) {

}
