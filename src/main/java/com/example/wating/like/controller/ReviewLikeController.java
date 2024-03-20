package com.example.wating.like.controller;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.like.service.interfaces.ReviewLikeService;
import com.example.wating.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ReviewLikeController.REVIEW_LIKE_URI_API)
public class ReviewLikeController {
  public static final String REVIEW_LIKE_URI_API = "/reviews/likes";
  private final ReviewLikeService reviewLikeService;

  @PostMapping("/{reviewId}")
  public ResponseEntity<StatusResponseDto> requestReviewLike(@PathVariable Long reviewId,@AuthenticationPrincipal
  UserDetailsImpl userDetails){
    StatusResponseDto statusResponseDto = reviewLikeService.requestReviewLike(userDetails.getUserId(),reviewId);
    return ResponseEntity.ok().body(statusResponseDto);
  }
}
