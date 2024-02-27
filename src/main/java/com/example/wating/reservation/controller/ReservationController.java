package com.example.wating.reservation.controller;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.reservation.dto.StoreReservationAddDto;
import com.example.wating.reservation.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/reservations")
public class ReservationController {
  private final ReservationService storeReservationService;
  @PostMapping("/{storeId}")
  public ResponseEntity<StatusResponseDto> addStoreReservation(@RequestBody
  StoreReservationAddDto storeReservationAddDto,@PathVariable Long storeId) {
    StatusResponseDto statusResponseDto =  storeReservationService.addStoreReservationDayInfoMonth(storeReservationAddDto,storeId);
    return ResponseEntity
        .status(HttpStatus.OK).body(statusResponseDto);
  }
}
