package com.example.wating.reservation.service;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.reservation.dao.ReservationRepository;
import com.example.wating.reservation.dto.GetStoreReservationDayRequestDto;
import com.example.wating.reservation.dto.GetStoreReservationRequestDto;
import com.example.wating.reservation.dto.StoreReservationAddDto;
import com.example.wating.reservation.dto.StoreReservationDayResponseDto;
import com.example.wating.reservation.dto.StoreReservationResponseDto;
import com.example.wating.reservation.entity.StoreReservation;
import com.example.wating.reservation.entity.StoreReservationInfo;
import com.example.wating.reservation.service.interfaces.ReservationService;
import com.example.wating.store.entity.Store;
import com.example.wating.store.service.interfaces.StoreService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
  private final ReservationRepository reservationRepository;
  private final StoreService storeService;

  @Override
  @Transactional
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

  @Override
  @Transactional
  public StoreReservationResponseDto getStoreReservationInfo(Long reservationId,
      GetStoreReservationRequestDto getStoreReservationRequestDto) {
    return reservationRepository.getStoreReservationByStoreIdAndDateInfo(reservationId,getStoreReservationRequestDto.years(),
        getStoreReservationRequestDto.months()).orElseThrow(
        () -> new IllegalArgumentException("일치하는 정보가 없습니다")
    );
  }

  @Override
  @Transactional
  public StoreReservationDayResponseDto getStoreReservationDayInfo(Long reservationId,
      GetStoreReservationDayRequestDto getStoreReservationDayDto) {
    StoreReservation storeReservation = getStoreReservationById(reservationId);
    Set<StoreReservationInfo> storeReservations = normalizationStoreReservation(storeReservation.getStoreReservationInfos());
    StoreReservationInfo storeReservationInfo = storeReservations.stream()
        .filter(reservation -> reservation.getDays().equals(getStoreReservationDayDto.days()) && reservation.getTimes().equals(getStoreReservationDayDto.time()))
        .findFirst()
        .orElseThrow(
            () -> new IllegalArgumentException("일치하는 정보가 없습니다")
        );
    return new StoreReservationDayResponseDto(storeReservationInfo.getIsPossible(),storeReservationInfo.getCapacity());
  }

  @Override
  public StoreReservation getStoreReservationById(Long storeReservationId) {
    return reservationRepository.getStoreReservationById(storeReservationId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );
  }

  //일 데이터 정규화
  @Override
  public Set<StoreReservationInfo> normalizationStoreReservation(String storeReservationInfos) {
    Gson gson = new Gson();
    return gson.fromJson(storeReservationInfos, new TypeToken<Set<StoreReservationInfo>>() {}.getType());
  }

  // 데이터 역정규화
  @Override
  public String toJsonStoreReservation(Set<StoreReservationInfo> storeReservationInfos) {
    Gson gson = new Gson();
    return gson.toJson(storeReservationInfos);
  }


}
