package com.exampleservice.reservation.service;

import com.exampleservice.reservation.dto.ReservationDTO;
import com.exampleservice.reservation.model.Reservation;
import com.exampleservice.reservation.repository.ReservationRepository;
import com.exampleservice.reservation.factory.ReservationFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationServiceImplTest {

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    public ReservationServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateReservation() {
        ReservationDTO dto = new ReservationDTO(null, 2L, 3L, LocalDate.now(), LocalDate.now().plusDays(5));
        Reservation reservation = ReservationFactory.createNewReservation(dto.getClientId(), dto.getVehicleId(), dto.getReservationDate(), dto.getReturnDate());
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        ReservationDTO created = reservationService.createReservation(dto);

        assertNotNull(created);
        assertEquals(dto.getClientId(), created.getClientId());
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void shouldReturnReservationById() {
        Reservation reservation = new Reservation(2L, 3L, LocalDate.now(), LocalDate.now().plusDays(5));
        reservation.setId(1L);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        ReservationDTO result = reservationService.getReservationById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(2L, result.getClientId());
    }
}
