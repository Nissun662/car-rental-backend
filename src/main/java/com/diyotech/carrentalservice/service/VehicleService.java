package com.diyotech.carrentalservice.service;

import com.diyotech.carrentalservice.entity.Vehicle;
import com.diyotech.carrentalservice.exception.VehicleNotFoundException;

import java.util.List;

public interface VehicleService {

    void saveVehicle(Vehicle vehicle);

    Vehicle getVehicle(Long vehicleId) throws VehicleNotFoundException;

    void updateVehicle(Long id, Vehicle vehicle) throws VehicleNotFoundException;

    void deleteVehicle(Long vehicleId) throws VehicleNotFoundException;

    List<Vehicle> getAllVehicles();

    void addMultiple(List<Vehicle> vehicles);
}
