package com.example.wating.like.dao.commentLike;

import static com.example.wating.comment.entity.QComment.comment;
import static com.example.wating.like.entity.commentLike.QCommentLike.commentLike;
import static com.example.wating.user.entity.QUser.user;

import com.example.wating.comment.dao.CommentRepositoryQuery;
import com.example.wating.comment.dto.CommentResponseDto;
import com.example.wating.like.entity.commentLike.CommentLikeId;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CommentLikeRepositoryQueryImpl implements CommentLikeRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  @Transactional
  public boolean existByCommentLikeId(CommentLikeId commentLikeId) {
    return jpaQueryFactory.from(commentLike).where(commentLike.commentLikeId.eq(commentLikeId)).select(commentLike.commentLikeId)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }


}
