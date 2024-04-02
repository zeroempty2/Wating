package com.example.wating.store.dao;

import com.example.wating.store.dto.StoreResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreRepositoryQuery {
  boolean existsByOwnerId(Long userId);
//  Page<StoreResponseDto> getStores(Pageable pageable);
}
