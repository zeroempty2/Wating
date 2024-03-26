package com.example.wating.store.service.interfaces;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.store.dto.AddStoreRequestDto;
import com.example.wating.store.dto.StoreResponseDto;
import com.example.wating.store.entity.Store;
import java.util.List;

public interface StoreService {
  StatusResponseDto addStore(AddStoreRequestDto addStoreRequestDto,Long UserId);

  Store findStoreByStoreId(Long storeId);

  List<StoreResponseDto> getStore();
}
