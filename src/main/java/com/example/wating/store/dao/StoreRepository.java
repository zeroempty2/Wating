package com.example.wating.store.dao;

import com.example.wating.store.entity.Store;
import org.springframework.data.repository.Repository;

public interface StoreRepository extends Repository<Store,Long> {
  void save(Store store);
}
