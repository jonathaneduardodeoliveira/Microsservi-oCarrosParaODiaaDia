package service.vehicle.factory;

import service.vehicle.model.Vehicle;

public class VehicleFactory {

    public static Vehicle createNewVehicle(String brand, String model, Integer year, String category, boolean available) {
        return new Vehicle(brand, model, year, category, available);
    }
}
