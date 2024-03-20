package com.example.wating.user.dao;

import com.example.wating.user.entity.User;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User,Long> {
  Optional<User> findByUsername(String username);
  Optional<User> findById(Long userId);
  void saveAndFlush(User user);
  void save(User user);
}
