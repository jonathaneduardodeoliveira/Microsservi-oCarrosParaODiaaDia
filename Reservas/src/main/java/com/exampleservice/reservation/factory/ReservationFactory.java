package com.exampleservice.reservation.factory;

import com.exampleservice.reservation.model.Reservation;

import java.time.LocalDate;

public class ReservationFactory {

    public static Reservation createNewReservation(Long clientId, Long vehicleId, LocalDate reservationDate, LocalDate returnDate) {
        return new Reservation(clientId, vehicleId, reservationDate, returnDate);
    }
}
