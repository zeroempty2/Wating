package com.example.wating.comment.dao;

import static com.example.wating.comment.entity.QComment.comment;
import static com.example.wating.user.entity.QUser.user;

import com.example.wating.comment.dto.CommentResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CommentRepositoryQueryImpl implements CommentRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  @Transactional
  public Optional<CommentResponseDto> responseCommentDtoByCommentId(Long commentId) {
    return Optional.ofNullable(
        jpaQueryFactory
            .select(
                Projections.bean(
                    CommentResponseDto.class
                    , comment.id
                    , comment.userId
                    , comment.commentContent
                    , user.nickName
                    , comment.createdAt
                    )
            )
            .from(comment)
            .leftJoin(user)
            .where(comment.id.eq(commentId),
                user.id.eq(comment.userId))
            .fetchFirst());
  }
  @Override
  @Transactional
  public List<CommentResponseDto> findCommentsByUserId(Long userId) {
    return jpaQueryFactory
        .select(
            Projections.bean(
                CommentResponseDto.class
                ,comment.id
                ,comment.userId
                ,comment.commentContent
                ,user.nickName
                ,comment.createdAt
                )
        )
        .from(comment)
        .leftJoin(user)
        .where(comment.userId.eq(userId),
            user.id.eq(comment.userId))
        .fetch();
  }


  @Override
  @Transactional
  public List<CommentResponseDto> findCommentsByReviewId(Long reviewId) {
    return jpaQueryFactory
        .select(
            Projections.bean(
                CommentResponseDto.class
                ,comment.id
                ,comment.userId
                ,comment.commentContent
                ,user.nickName
                ,comment.createdAt
            )
        )
        .from(comment)
        .leftJoin(user)
        .where(comment.review.id.eq(reviewId),
            user.id.eq(comment.userId))
        .fetch();
  }


}
