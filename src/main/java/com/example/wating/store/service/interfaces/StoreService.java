package com.example.wating.store.service.interfaces;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.store.dto.AddStoreRequestDto;

public interface StoreService {
  StatusResponseDto addStore(AddStoreRequestDto addStoreRequestDto);
}
