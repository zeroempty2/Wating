package com.example.wating.reservation.controller;

import static com.example.wating.reservation.controller.ReservationController.RESERVATION_URI_API;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.reservation.dto.GetStoreReservationDayRequestDto;
import com.example.wating.reservation.dto.GetStoreReservationRequestDto;
import com.example.wating.reservation.dto.StoreReservationAddDto;
import com.example.wating.reservation.dto.StoreReservationDayResponseDto;
import com.example.wating.reservation.dto.StoreReservationResponseDto;
import com.example.wating.reservation.dto.UpdateStoreReservationDto;
import com.example.wating.reservation.service.interfaces.ReservationService;
import com.example.wating.security.UserDetailsImpl;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(RESERVATION_URI_API)
public class ReservationController {
  public static final String RESERVATION_URI_API = "/stores/reservations";
  private final ReservationService storeReservationService;
  @PostMapping("/{storeId}")
  public ResponseEntity<StatusResponseDto> addStoreReservation(@RequestBody
  StoreReservationAddDto storeReservationAddDto,@PathVariable Long storeId) {
    StatusResponseDto statusResponseDto =  storeReservationService.addStoreReservationDayInfoMonth(storeReservationAddDto,storeId);
    return ResponseEntity
        .status(HttpStatus.OK).body(statusResponseDto);
  }

  //월단위 예약 조회
  @GetMapping("/{storeId}")
  public ResponseEntity<StoreReservationResponseDto> getStoreReservationInfo(@PathVariable Long storeId,@RequestBody
  GetStoreReservationRequestDto getStoreReservationRequestDto){
    StoreReservationResponseDto storeReservationResponseDto = storeReservationService.getStoreReservationInfo(storeId,getStoreReservationRequestDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(storeReservationResponseDto);
  }
  //일단위 예약 조회
  @GetMapping("days/{storeReservationId}")
  public ResponseEntity<StoreReservationDayResponseDto> getStoreReservationDayInfo(@PathVariable Long storeReservationId,@RequestBody
  GetStoreReservationDayRequestDto getStoreReservationDayDto){
    StoreReservationDayResponseDto storeReservationDayResponseDto = storeReservationService.getStoreReservationDayInfo(storeReservationId,getStoreReservationDayDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(storeReservationDayResponseDto);
  }
  //예약 정보 변경
  @PatchMapping("/{storeReservationId}")
  public ResponseEntity<StatusResponseDto> updateStoreReservation(@PathVariable Long storeReservationId,@RequestBody
  UpdateStoreReservationDto updateStoreReservationDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
    StatusResponseDto statusResponseDto = storeReservationService.updateStoreReservation(updateStoreReservationDto,storeReservationId,
        userDetails.getUserId());
    return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
  }
}
