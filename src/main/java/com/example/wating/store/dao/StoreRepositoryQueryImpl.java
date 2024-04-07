package com.example.wating.store.dao;

import static com.example.wating.comment.entity.QComment.comment;
import static com.example.wating.store.entity.QStore.store;
import static com.example.wating.user.entity.QUser.user;

import com.example.wating.comment.dto.CommentResponseDto;
import com.example.wating.store.dto.StoreResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class StoreRepositoryQueryImpl implements StoreRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;
  @Override
  @Transactional(readOnly = true)
  public boolean existsByOwnerId(Long userId) {
    return jpaQueryFactory.from(store).where(store.ownerId.eq(userId)).select(store.ownerId)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }

//  @Override
//  @Transactional(readOnly = true)
//  public Page<StoreResponseDto> getStores(Pageable pageable) {
//    List<StoreResponseDto> dtoList = jpaQueryFactory
//        .select(
//            Projections.bean(
//                StoreResponseDto.class
//                ,store.id
//                ,store.storeName
//                ,store.starRate
//            )
//        )
//        .from(store)
//        .setHint("org.hibernate.readOnly", true)
//        .orderBy(store.createdAt.desc())
//        .limit(pageable.getPageSize())
//        .offset(pageable.getOffset())
//        .fetch();
//
//    long totalSize = storeCountQuery().fetchCount();
//
//    return PageableExecutionUtils.getPage(dtoList, pageable, () -> totalSize);
//  }
  private Page<StoreResponseDto> getStoresAndSortByKeyword(){

  }
  private Page<StoreResponseDto> getStoresAndCutByStarRate(){

  }
//
//  private JPAQuery<Long> storeCountQuery() {
//    return jpaQueryFactory.select(Wildcard.count)
//        .from(store);
//  }
}
