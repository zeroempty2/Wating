package com.example.wating.store.service.interfaces;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.store.dto.AddStoreRequestDto;
import com.example.wating.store.dto.StoreDetailsResponseDto;
import com.example.wating.store.dto.StorePageDto;
import com.example.wating.store.dto.StoreResponseDto;
import com.example.wating.store.entity.Store;
import java.util.List;
import org.springframework.data.domain.Page;

public interface StoreService {
  StatusResponseDto addStore(AddStoreRequestDto addStoreRequestDto,Long UserId);

  Store findStoreByStoreId(Long storeId);

  Page<StoreResponseDto> getStores(StorePageDto storePageDto);

  StoreDetailsResponseDto getStore(Long storeId);

}
