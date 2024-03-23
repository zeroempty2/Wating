package com.example.wating.review.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@Getter
@Setter
public class StoreReviewResponseDto{
  private  Long id;

  private  String nickName;

  private String reviewTitle;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
  private LocalDateTime createdAt;

  private Long likeCount;
}
