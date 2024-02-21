package com.example.wating.review.dao;


import com.example.wating.review.entity.Review;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReviewRepository extends Repository<Review,Long> {
  void save(Review review);
  Optional<Review> findById(Long reviewId);
}
