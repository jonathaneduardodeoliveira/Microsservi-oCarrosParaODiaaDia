package com.exampleservice.reservation.controller;

import com.exampleservice.reservation.dto.ReservationDTO;
import com.exampleservice.reservation.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    void shouldCreateReservation() throws Exception {
        ReservationDTO reservationDTO = new ReservationDTO(1L, 2L, 3L, LocalDate.now(), LocalDate.now().plusDays(3));
        when(reservationService.createReservation(Mockito.any())).thenReturn(reservationDTO);

        mockMvc.perform(post("/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"clientId\":2,\"vehicleId\":3,\"reservationDate\":\"2024-11-13\",\"returnDate\":\"2024-11-16\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clientId").value(2))
                .andExpect(jsonPath("$.vehicleId").value(3));
    }

    @Test
    void shouldGetAllReservations() throws Exception {
        ReservationDTO reservation1 = new ReservationDTO(1L, 2L, 3L, LocalDate.now(), LocalDate.now().plusDays(3));
        ReservationDTO reservation2 = new ReservationDTO(2L, 4L, 5L, LocalDate.now(), LocalDate.now().plusDays(7));
        when(reservationService.getAllReservations()).thenReturn(Arrays.asList(reservation1, reservation2));

        mockMvc.perform(get("/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
