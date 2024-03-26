package com.example.wating.review.dao;

import static com.example.wating.like.entity.reviewLike.QReviewLike.reviewLike;
import static com.example.wating.review.entity.QReview.review;
import static com.example.wating.user.entity.QUser.user;

import com.example.wating.review.dto.ReviewResponseDto;
import com.example.wating.review.dto.StoreReviewResponseDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewRepositoryQueryImpl implements ReviewRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<StoreReviewResponseDto> findReviewListByStoreId(Long storeId) {
    return jpaQueryFactory
        .select(
            Projections.bean(
                StoreReviewResponseDto.class
                ,review.id
                ,user.nickName
                ,review.reviewTitle
                ,review.createdAt
                , ExpressionUtils.as
                    (
                        JPAExpressions.select(Wildcard.count)
                            .from(reviewLike)
                            .leftJoin(reviewLike.review)
                            .where(reviewLikeEqByReviewId(review.id)),
                        "likeCount"))
        )
        .from(review)
        .leftJoin(user).on(review.userId.eq(user.id))
        .where(review.storeId.eq(storeId))
        .fetch();
  }

  @Override
  public ReviewResponseDto getReviewByReviewId(Long reviewId) {
    return
        jpaQueryFactory
            .select(
                Projections.bean(
                    ReviewResponseDto.class
                    , review.id
                    , user.nickName
                    , review.reviewTitle
                    , review.reviewContent
                    ,review.tasteRating
                    ,review.atmosphereRating
                    ,review.serviceRating
                    ,review.totalRating
                    ,review.createdAt
                    , ExpressionUtils.as
                        (
                            JPAExpressions.select(Wildcard.count)
                                .from(reviewLike)
                                .leftJoin(reviewLike.review)
                                .where(reviewLikeEqByReviewId(review.id)),
                            "likeCount")
                )
            )
            .from(review)
            .leftJoin(user).on(review.userId.eq(user.id))
            .where(review.id.eq(reviewId))
            .fetchFirst();
  }



  private BooleanExpression reviewLikeEqByReviewId(NumberPath<Long> reviewId) {
    return Objects.nonNull(reviewId) ? reviewLike.review.id.eq(reviewId) : null;
  }
}
