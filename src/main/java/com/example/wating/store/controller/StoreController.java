package com.example.wating.store.controller;

import static com.example.wating.store.controller.StoreController.STORE_URI_API;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.review.controller.ReviewController;
import com.example.wating.security.UserDetailsImpl;
import com.example.wating.store.dto.AddStoreRequestDto;
import com.example.wating.store.dto.StorePageDto;
import com.example.wating.store.dto.StoreResponseDto;
import com.example.wating.store.service.interfaces.StoreService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

  @GetMapping
  public ResponseEntity<Page<StoreResponseDto>> getStores(@ModelAttribute StorePageDto storePageDto){
    Page<StoreResponseDto> storeList = storeService.getStores(storePageDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(storeList);
  }

}
