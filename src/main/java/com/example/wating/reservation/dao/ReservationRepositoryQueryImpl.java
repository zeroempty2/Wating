package com.example.wating.reservation.dao;

import static com.example.wating.reservation.entity.QStoreReservation.storeReservation;


import com.example.wating.reservation.dto.StoreReservationResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
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

  @Override
  @Transactional
  public Optional<StoreReservationResponseDto> getStoreReservationByStoreIdAndDateInfo(
      Long storeId, Short years, Byte months) {
    return Optional.ofNullable(jpaQueryFactory
        .select(
            Projections.constructor(
                StoreReservationResponseDto.class
                , storeReservation.id
                , storeReservation.storeReservationInfos
            )
        )
        .from(storeReservation)
        .where(storeReservation.store.id.eq(storeId)
            , storeReservation.years.eq(years)
            , storeReservation.months.eq(months)
        )
        .leftJoin(storeReservation.store)
        .fetchOne());
  }
}
