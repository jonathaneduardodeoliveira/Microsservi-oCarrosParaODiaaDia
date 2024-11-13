package com.exampleservice.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exampleservice.reservation.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
