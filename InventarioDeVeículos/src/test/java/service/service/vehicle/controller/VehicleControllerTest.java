package service.vehicle.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import service.vehicle.dto.VehicleDTO;
import service.vehicle.service.VehicleService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VehicleControllerTest {

    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVehicle() {
        VehicleDTO vehicleDTO = new VehicleDTO(null, "Ford", "Focus", 2021, "Sedan", true);
        VehicleDTO savedVehicle = new VehicleDTO(1L, "Ford", "Focus", 2021, "Sedan", true);

        when(vehicleService.createVehicle(vehicleDTO)).thenReturn(savedVehicle);

        ResponseEntity<VehicleDTO> response = vehicleController.createVehicle(vehicleDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(savedVehicle, response.getBody());
    }

    @Test
    void testGetVehicleById() {
        Long id = 1L;
        VehicleDTO vehicleDTO = new VehicleDTO(id, "Ford", "Focus", 2021, "Sedan", true);

        when(vehicleService.getVehicleById(id)).thenReturn(vehicleDTO);

        ResponseEntity<VehicleDTO> response = vehicleController.getVehicleById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(vehicleDTO, response.getBody());
    }

    @Test
    void testGetAllVehicles() {
        List<VehicleDTO> vehicles = Arrays.asList(
                new VehicleDTO(1L, "Ford", "Focus", 2021, "Sedan", true),
                new VehicleDTO(2L, "Toyota", "Corolla", 2022, "Sedan", true)
        );

        when(vehicleService.getAllVehicles()).thenReturn(vehicles);

        ResponseEntity<List<VehicleDTO>> response = vehicleController.getAllVehicles();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(vehicles, response.getBody());
    }

    @Test
    void testUpdateVehicle() {
        Long id = 1L;
        VehicleDTO updatedVehicle = new VehicleDTO(id, "Ford", "Focus", 2021, "Sedan", false);

        when(vehicleService.updateVehicle(eq(id), any(VehicleDTO.class))).thenReturn(updatedVehicle);

        ResponseEntity<VehicleDTO> response = vehicleController.updateVehicle(id, updatedVehicle);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedVehicle, response.getBody());
    }

    @Test
    void testDeleteVehicle() {
        Long id = 1L;

        doNothing().when(vehicleService).deleteVehicle(id);

        ResponseEntity<Void> response = vehicleController.deleteVehicle(id);

        assertEquals(204, response.getStatusCodeValue());
        verify(vehicleService, times(1)).deleteVehicle(id);
    }
}
