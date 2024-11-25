package com.exampleservice.reservation.repository;

import com.exampleservice.reservation.model.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void shouldSaveAndFindReservationById() {
        Reservation reservation = new Reservation(2L, 3L, LocalDate.now(), LocalDate.now().plusDays(5));
        Reservation saved = reservationRepository.save(reservation);

        Optional<Reservation> found = reservationRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals(reservation.getClientId(), found.get().getClientId());
    }
}
