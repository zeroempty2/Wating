package com.example.wating.comment.entity;

import com.example.wating.comment.dto.CommentRequestDto;
import com.example.wating.review.entity.Review;
import com.example.wating.user.entity.User;
import com.example.wating.util.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends TimeStamped {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Long userId;

  @Column
  private String commentContent;

  //생성자
  @Builder
  public Comment(Long userId, Review review, String commentContent){
    this.userId = userId;
    this.review = review;
    this.commentContent = commentContent;
  }

  //연관관계
  @ManyToOne
  @JoinColumn(name = "review_id")
  private Review review;


  //메서드
  public boolean isWriter(User user,Comment comment){
    return Objects.equals(user.getId(), comment.getUserId());
  }

  public void update(CommentRequestDto commentRequestDto) {
    this.commentContent = commentRequestDto.commentContents();
  }
}
