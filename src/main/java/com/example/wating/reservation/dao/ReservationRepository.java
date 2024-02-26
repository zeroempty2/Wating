package com.example.wating.reservation.dao;

import com.example.wating.reservation.entity.StoreReservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<StoreReservation, String> {

}
