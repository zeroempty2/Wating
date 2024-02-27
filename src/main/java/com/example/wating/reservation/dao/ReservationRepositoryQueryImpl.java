package com.example.wating.reservation.dao;

import static com.example.wating.reservation.entity.QStoreReservation.storeReservation;
import static com.example.wating.store.entity.QStore.store;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReservationRepositoryQueryImpl implements ReservationRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;
  @Override
  @Transactional(readOnly = true)
  public boolean existsReservationByYearsAndMonths(Short years, Byte months) {
    return jpaQueryFactory.from(storeReservation).where(storeReservation.years.eq(years),storeReservation.months.eq(months)).select(storeReservation.years,storeReservation.months)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }
}
