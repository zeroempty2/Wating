package com.example.wating.store.dao;

import com.example.wating.store.entity.Store;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface StoreRepository extends Repository<Store,Long>,StoreRepositoryQuery {
  void save(Store store);
  void saveAndFlush(Store store);
  Optional<Store> findById(Long storeId);
  Optional<Store> findByOwnerId(Long ownerId);
  Page<Store> findAll(Pageable pageable);
}
