package com.example.wating.store.dao;

import com.example.wating.store.dto.StoreResponseDto;
import java.util.List;

public interface StoreRepositoryQuery {
  boolean existsByOwnerId(Long userId);
  List<StoreResponseDto> getStores();
}
