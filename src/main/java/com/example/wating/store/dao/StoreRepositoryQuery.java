package com.example.wating.store.dao;

public interface StoreRepositoryQuery {
  boolean existsByOwnerId(Long userId);
}
