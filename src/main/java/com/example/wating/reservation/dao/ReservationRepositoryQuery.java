package com.example.wating.reservation.dao;

public interface ReservationRepositoryQuery {
  boolean existsReservationByYearsAndMonths(Short years, Byte months);
}
