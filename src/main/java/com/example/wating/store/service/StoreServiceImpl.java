package com.example.wating.store.service;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.store.dao.StoreRepository;
import com.example.wating.store.dto.AddStoreRequestDto;
import com.example.wating.store.dto.StoreDetailsResponseDto;
import com.example.wating.store.dto.StorePageDto;
import com.example.wating.store.dto.StoreResponseDto;
import com.example.wating.store.entity.Store;
import com.example.wating.store.service.interfaces.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
  private final StoreRepository storeRepository;
  @Override
  @Transactional
  //store 중복 등록 방지 방법 필요
  public StatusResponseDto addStore(AddStoreRequestDto addStoreRequestDto,Long userId) {

    if(storeRepository.existsByOwnerId(userId)){
      throw new IllegalArgumentException("중복된 등록 요청입니다");
    }

    Store store = Store.builder()
        .aboutStore(addStoreRequestDto.aboutStore())
        .storeName(addStoreRequestDto.storeName())
        .build();

      storeRepository.save(store);

    return new StatusResponseDto(201,"Created");
  }

  @Override
  public Store findStoreByStoreId(Long storeId) {
    return storeRepository.findById(storeId).orElseThrow(
        () -> new IllegalArgumentException("일치하는 정보가 없습니다")
    );
  }

  @Override
  @Transactional(readOnly = true)
  public Page<StoreResponseDto> getStores(StorePageDto storePageDto) {
    return storeRepository.getStores(storePageDto);

  }
  @Override
  @Transactional(readOnly = true)
  public StoreDetailsResponseDto getStore(Long storeId) {
    Store store = findStoreByStoreId(storeId);
    return new StoreDetailsResponseDto(store.getStoreName(),store.getStarRate(),store.getAboutStore());
  }


}
