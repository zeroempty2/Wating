package com.example.wating.reservation.dao;

import com.example.wating.reservation.entity.StoreReservation;
import java.util.Optional;
import org.springframework.data.repository.Repository;


public interface ReservationRepository extends Repository<StoreReservation, Long>,ReservationRepositoryQuery {
  void save(StoreReservation storeReservation);
  Optional<StoreReservation> getStoreReservationById(Long storeReservationId);
  void deleteById(Long storeReservationId);
}
