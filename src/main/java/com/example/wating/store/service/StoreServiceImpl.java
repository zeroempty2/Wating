package com.example.wating.store.service;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.store.dao.StoreRepository;
import com.example.wating.store.dto.AddStoreRequestDto;
import com.example.wating.store.entity.Store;
import com.example.wating.store.service.interfaces.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
  private final StoreRepository storeRepository;
  @Override
  @Transactional
  //store 중복 등록 방지 방법 필요
  public StatusResponseDto addStore(AddStoreRequestDto addStoreRequestDto) {
    Store store = Store.builder()
        .aboutStore(addStoreRequestDto.aboutStore())
        .storeName(addStoreRequestDto.storeName())
        .build();

      storeRepository.save(store);

    return new StatusResponseDto(201,"Created");
  }
}
