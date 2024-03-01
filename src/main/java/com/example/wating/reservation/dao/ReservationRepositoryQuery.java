package com.example.wating.reservation.dao;


import com.example.wating.reservation.dto.StoreReservationResponseDto;
import java.util.Optional;

public interface ReservationRepositoryQuery {
  boolean existsReservationByYearsAndMonths(Short years, Byte months);
  Optional<StoreReservationResponseDto> getStoreReservationByStoreIdAndDateInfo(Long storeId, Short years, Byte months);

}
