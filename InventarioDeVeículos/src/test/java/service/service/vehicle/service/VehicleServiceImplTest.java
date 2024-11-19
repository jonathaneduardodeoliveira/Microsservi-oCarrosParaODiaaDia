package service.vehicle.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.vehicle.dto.VehicleDTO;
import service.vehicle.model.Vehicle;
import service.vehicle.repository.VehicleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VehicleServiceImplTest {

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVehicle() {
        Vehicle vehicle = new Vehicle("Ford", "Focus", 2021, "Sedan", true);
        Vehicle savedVehicle = new Vehicle("Ford", "Focus", 2021, "Sedan", true);
        savedVehicle.setId(1L);

        VehicleDTO vehicleDTO = new VehicleDTO(null, "Ford", "Focus", 2021, "Sedan", true);
        VehicleDTO expectedDTO = new VehicleDTO(1L, "Ford", "Focus", 2021, "Sedan", true);

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(savedVehicle);

        VehicleDTO result = vehicleService.createVehicle(vehicleDTO);

        assertEquals(expectedDTO, result);
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    }

    @Test
    void testGetVehicleById() {
        Long id = 1L;
        Vehicle vehicle = new Vehicle("Ford", "Focus", 2021, "Sedan", true);
        vehicle.setId(id);

        when(vehicleRepository.findById(id)).thenReturn(Optional.of(vehicle));

        VehicleDTO result = vehicleService.getVehicleById(id);

        assertEquals("Ford", result.getBrand());
        assertEquals("Focus", result.getModel());
    }

    @Test
    void testGetAllVehicles() {
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle("Ford", "Focus", 2021, "Sedan", true),
                new Vehicle("Toyota", "Corolla", 2022, "Sedan", true)
        );

        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<VehicleDTO> result = vehicleService.getAllVehicles();

        assertEquals(2, result.size());
        assertEquals("Ford", result.get(0).getBrand());
    }

    @Test
    void testUpdateVehicle() {
        Long id = 1L;
        Vehicle vehicle = new Vehicle("Ford", "Focus", 2021, "Sedan", true);
        vehicle.setId(id);

        Vehicle updatedVehicle = new Vehicle("Ford", "Focus", 2021, "Sedan", false);
        updatedVehicle.setId(id);

        VehicleDTO updateDTO = new VehicleDTO(null, "Ford", "Focus", 2021, "Sedan", false);

        when(vehicleRepository.findById(id)).thenReturn(Optional.of(vehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(updatedVehicle);

        VehicleDTO result = vehicleService.updateVehicle(id, updateDTO);

        assertEquals(false, result.isAvailable());
    }

    @Test
    void testDeleteVehicle() {
        Long id = 1L;

        doNothing().when(vehicleRepository).deleteById(id);

        vehicleService.deleteVehicle(id);

        verify(vehicleRepository, times(1)).deleteById(id);
    }
}
