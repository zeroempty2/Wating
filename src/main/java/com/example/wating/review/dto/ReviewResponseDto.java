package com.example.wating.review.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReviewResponseDto {
  private Long id;

  private String nickName;

  private String reviewTitle;

  private String reviewContent;

  private Float tasteRating;

  private Float atmosphereRating;

  private Float serviceRating;

  private Float totalRating;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
  private LocalDateTime createdAt;
  private Long likeCount;
}
