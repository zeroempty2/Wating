package com.example.wating.store.dao;

import static com.example.wating.comment.entity.QComment.comment;
import static com.example.wating.store.entity.QStore.store;
import static com.example.wating.user.entity.QUser.user;

import com.example.wating.comment.dto.CommentResponseDto;
import com.example.wating.store.dto.StorePageDto;
import com.example.wating.store.dto.StoreResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

  @Override
  @Transactional(readOnly = true)
  public Page<StoreResponseDto> getStores(StorePageDto storePageDto) {
    Pageable pageable = storePageDto.toPageable();
    int starCut = storePageDto.getStarCut();
    List<StoreResponseDto> dtoList;

    if(!Objects.isNull(storePageDto.getSortBy())) dtoList = getStoresAndSortByKeyword(pageable);
    else if(storePageDto.isCutByStarRate()) dtoList =  getStoresAndCutByStarRate(pageable,starCut);
    else dtoList = getStoresSortByDesc(pageable);


    long totalSize = storeCountQuery().fetchCount();

    return PageableExecutionUtils.getPage(dtoList, pageable, () -> totalSize);
  }
  private List<StoreResponseDto> getStoresAndSortByKeyword(Pageable pageable){
    return jpaQueryFactory
        .select(
            Projections.bean(
                StoreResponseDto.class
                ,store.id
                ,store.storeName
                ,store.starRate
            )
        )
        .from(store)
        .setHint("org.hibernate.readOnly", true)
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
  }
  private List<StoreResponseDto> getStoresAndCutByStarRate(Pageable pageable, int starCut){
    return jpaQueryFactory
        .select(
            Projections.bean(
                StoreResponseDto.class
                ,store.id
                ,store.storeName
                ,store.starRate
            )
        )
        .from(store)
        .where(store.starRate.gt(starCut))
        .setHint("org.hibernate.readOnly", true)
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
  }
  private List<StoreResponseDto> getStoresSortByDesc(Pageable pageable){
    return jpaQueryFactory
        .select(
            Projections.bean(
                StoreResponseDto.class
                ,store.id
                ,store.storeName
                ,store.starRate
            )
        )
        .from(store)
        .setHint("org.hibernate.readOnly", true)
        .orderBy(store.createdAt.desc())
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
  }
//
  private JPAQuery<Long> storeCountQuery() {
    return jpaQueryFactory.select(Wildcard.count)
        .from(store);
  }
}
