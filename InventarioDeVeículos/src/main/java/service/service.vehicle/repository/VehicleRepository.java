package service.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.vehicle.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
