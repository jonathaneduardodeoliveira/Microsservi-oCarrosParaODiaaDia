package service.vehicle.service;

import service.vehicle.dto.VehicleDTO;
import java.util.List;

public interface VehicleService {
    VehicleDTO createVehicle(VehicleDTO vehicleDTO);
    VehicleDTO getVehicleById(Long id);
    List<VehicleDTO> getAllVehicles();
    VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO);
    void deleteVehicle(Long id);
}
