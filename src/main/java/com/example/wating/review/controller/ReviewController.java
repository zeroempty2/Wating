package com.example.wating.review.controller;


import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.review.dto.ReviewRequestDto;
import com.example.wating.review.dto.ReviewResponseDto;
import com.example.wating.review.dto.StoreReviewResponseDto;
import com.example.wating.review.service.ReviewServiceImpl;
import com.example.wating.security.UserDetailsImpl;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ReviewController.REVIEW_URI_API)
public class ReviewController {

  public static final String REVIEW_URI_API = "/reviews";
  private final ReviewServiceImpl reviewService;

  @PostMapping("/{storeId}")
  public ResponseEntity<StatusResponseDto> addReview(@RequestBody ReviewRequestDto reviewRequestDto,
      @PathVariable Long storeId, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    StatusResponseDto statusResponseDto = reviewService.addReview(userDetails.getUser(),
        reviewRequestDto, storeId);
    return ResponseEntity.ok().body(statusResponseDto);
  }

  @GetMapping("/review/{reviewId}")
  public ResponseEntity<ReviewResponseDto> getReview(@PathVariable Long reviewId) {
    ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(reviewResponseDto);
  }

  @GetMapping("/review/{storeId}")
  public ResponseEntity<List<StoreReviewResponseDto>> getStoreReviews(@PathVariable Long storeId) {
    List<StoreReviewResponseDto> responseDto = reviewService.getStoreReviews(storeId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(responseDto);
  }

}
