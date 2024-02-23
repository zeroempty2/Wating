package com.example.wating.store.dao;

import static com.example.wating.store.entity.QStore.store;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
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
}
