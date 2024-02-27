package com.example.wating.reservation.service.interfaces;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.reservation.dto.StoreReservationAddDto;

public interface ReservationService {
  StatusResponseDto addStoreReservationDayInfoMonth(StoreReservationAddDto storeReservationAddDto,Long storeId);
}
