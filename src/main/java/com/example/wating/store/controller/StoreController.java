package com.example.wating.store.controller;

import static com.example.wating.store.controller.StoreController.STORE_URI_API;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.review.controller.ReviewController;
import com.example.wating.security.UserDetailsImpl;
import com.example.wating.store.dto.AddStoreRequestDto;
import com.example.wating.store.service.interfaces.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(STORE_URI_API)
public class StoreController {
  private final StoreService storeService;

  public static final String STORE_URI_API = "/stores";
  @PostMapping
  public ResponseEntity<StatusResponseDto> AddStore(@RequestBody AddStoreRequestDto addStoreRequestDto, @AuthenticationPrincipal
      UserDetailsImpl userDetails){
    StatusResponseDto statusResponseDto = storeService.addStore(addStoreRequestDto,
        userDetails.getUserId());
    return ResponseEntity.ok().body(statusResponseDto);
  }

}
