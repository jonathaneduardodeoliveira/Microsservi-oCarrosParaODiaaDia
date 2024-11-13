package com.exampleservice.reservation.dto;

import java.time.LocalDate;

public class ReservationDTO {

    private Long id;
    private Long clientId;
    private Long vehicleId;
    private LocalDate reservationDate;
    private LocalDate returnDate;

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, Long clientId, Long vehicleId, LocalDate reservationDate, LocalDate returnDate) {
        this.id = id;
        this.clientId = clientId;
        this.vehicleId = vehicleId;
        this.reservationDate = reservationDate;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
