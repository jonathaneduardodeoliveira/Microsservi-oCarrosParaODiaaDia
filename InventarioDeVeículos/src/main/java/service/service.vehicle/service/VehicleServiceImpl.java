package service.vehicle.service;

import service.vehicle.dto.VehicleDTO;
import service.vehicle.model.Vehicle;
import service.vehicle.repository.VehicleRepository;
import service.vehicle.factory.VehicleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = VehicleFactory.createNewVehicle(
                vehicleDTO.getBrand(),
                vehicleDTO.getModel(),
                vehicleDTO.getYear(),
                vehicleDTO.getCategory(),
                vehicleDTO.isAvailable()
        );
        vehicle = vehicleRepository.save(vehicle);
        return new VehicleDTO(vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getCategory(), vehicle.isAvailable());
    }

    @Override
    public VehicleDTO getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> new VehicleDTO(vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getCategory(), vehicle.isAvailable()))
                .orElse(null);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicle -> new VehicleDTO(vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getCategory(), vehicle.isAvailable()))
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicle.setBrand(vehicleDTO.getBrand());
            vehicle.setModel(vehicleDTO.getModel());
            vehicle.setYear(vehicleDTO.getYear());
            vehicle.setCategory(vehicleDTO.getCategory());
            vehicle.setAvailable(vehicleDTO.isAvailable());
            vehicleRepository.save(vehicle);
            return new VehicleDTO(vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getCategory(), vehicle.isAvailable());
        }).orElse(null);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
