package com.example.wating.reservation.service.interfaces;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.reservation.dto.GetStoreReservationDayRequestDto;
import com.example.wating.reservation.dto.GetStoreReservationRequestDto;
import com.example.wating.reservation.dto.StoreReservationAddDto;
import com.example.wating.reservation.dto.StoreReservationDayResponseDto;
import com.example.wating.reservation.dto.StoreReservationResponseDto;
import com.example.wating.reservation.dto.UpdateStoreReservationDto;
import com.example.wating.reservation.entity.StoreReservation;
import com.example.wating.reservation.entity.StoreReservationInfo;
import java.util.Set;

public interface ReservationService {
  StatusResponseDto addStoreReservationDayInfoMonth(StoreReservationAddDto storeReservationAddDto,Long storeId);
  StoreReservationResponseDto getStoreReservationInfo(Long reservationId, GetStoreReservationRequestDto getStoreReservationRequestDto);
  StoreReservationDayResponseDto getStoreReservationDayInfo(Long reservationId, GetStoreReservationDayRequestDto getStoreReservationDayDto);
  StatusResponseDto deleteStoreReservation(Long reservationId,Long userId);
  StoreReservation findStoreReservationById(Long storeReservationId);
  StatusResponseDto updateStoreReservation(UpdateStoreReservationDto updateStoreReservationDto,Long storeReservationId, Long userId);
  Set<StoreReservationInfo> normalizationStoreReservation(String storeReservationMonthInfo);
  String toJsonStoreReservation(Set<StoreReservationInfo>storeReservationDayInfos);
}
