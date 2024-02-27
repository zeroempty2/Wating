package com.example.wating.reservation.service;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.reservation.dao.ReservationRepository;
import com.example.wating.reservation.dto.StoreReservationAddDto;
import com.example.wating.reservation.entity.StoreReservation;
import com.example.wating.reservation.entity.StoreReservationInfo;
import com.example.wating.reservation.service.interfaces.ReservationService;
import com.example.wating.store.entity.Store;
import com.example.wating.store.service.interfaces.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
  private final ReservationRepository reservationRepository;
  private final StoreService storeService;

  @Override
  public StatusResponseDto addStoreReservationDayInfoMonth(
      StoreReservationAddDto storeReservationAddDto, Long storeId) {
    Store store = storeService.findStoreByStoreId(storeId);

    if(reservationRepository.existsReservationByYearsAndMonths(
        storeReservationAddDto.years(),storeReservationAddDto.months())){
     return new StatusResponseDto(204,"No Content");
    }

    StoreReservation storeReservationInfo = StoreReservation.builder()
        .store(store)
        .months(storeReservationAddDto.months())
        .years(storeReservationAddDto.years())
        .storeReservationInfos(storeReservationAddDto.StoreReservationInfos())
        .build();

    reservationRepository.save(storeReservationInfo);
    return new StatusResponseDto(200,"Success");
  }
}
