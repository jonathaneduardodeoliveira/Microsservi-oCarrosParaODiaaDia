package com.exampleservice.reservation.service;

import com.exampleservice.reservation.dto.ReservationDTO;
import com.exampleservice.reservation.model.Reservation;
import com.exampleservice.reservation.repository.ReservationRepository;
import com.exampleservice.reservation.factory.ReservationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = ReservationFactory.createNewReservation(
                reservationDTO.getClientId(),
                reservationDTO.getVehicleId(),
                reservationDTO.getReservationDate(),
                reservationDTO.getReturnDate()
        );
        reservation = reservationRepository.save(reservation);
        return new ReservationDTO(reservation.getId(), reservation.getClientId(), reservation.getVehicleId(), reservation.getReservationDate(), reservation.getReturnDate());
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(reservation -> new ReservationDTO(reservation.getId(), reservation.getClientId(), reservation.getVehicleId(), reservation.getReservationDate(), reservation.getReturnDate()))
                .orElse(null);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservation -> new ReservationDTO(reservation.getId(), reservation.getClientId(), reservation.getVehicleId(), reservation.getReservationDate(), reservation.getReturnDate()))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setClientId(reservationDTO.getClientId());
            reservation.setVehicleId(reservationDTO.getVehicleId());
            reservation.setReservationDate(reservationDTO.getReservationDate());
            reservation.setReturnDate(reservationDTO.getReturnDate());
            reservationRepository.save(reservation);
            return new ReservationDTO(reservation.getId(), reservation.getClientId(), reservation.getVehicleId(), reservation.getReservationDate(), reservation.getReturnDate());
        }).orElse(null);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
