package com.diyotech.carrentalservice.service.Impl;


import com.diyotech.carrentalservice.entity.Vehicle;
import com.diyotech.carrentalservice.exception.VehicleNotFoundException;
import com.diyotech.carrentalservice.repository.VehicleRepository;
import com.diyotech.carrentalservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    // 1. Method to save vehicle
    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    // 2. Method to get vehicle by id
    @Override
    public Vehicle getVehicle(Long vehicleId) throws VehicleNotFoundException {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        if (vehicleOptional.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle with id " + vehicleId + " not found");
        }
        return vehicleOptional.get();
    }

    // 3. Method to update vehicle details
    @Override
    public void updateVehicle(Long id, Vehicle vehicle) throws VehicleNotFoundException {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with id " + id + " not found"));

        if(vehicle.getMake() != null){
            existingVehicle.setMake(vehicle.getMake());
        }
        if(vehicle.getModel() != null){
            existingVehicle.setModel(vehicle.getModel());
        }
        if(vehicle.getYear() != null){
            existingVehicle.setYear(vehicle.getYear());
        }
        if(vehicle.getMileage() != null){
            existingVehicle.setMileage(vehicle.getMileage());
        }
        if(vehicle.getVehicleType() != null){
            existingVehicle.setVehicleType(vehicle.getVehicleType());
        }

        vehicleRepository.save(existingVehicle);
    }

    @Override
    public void deleteVehicle(Long vehicleId) throws VehicleNotFoundException {
        Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(()-> new VehicleNotFoundException("Vehicle not found having is :" + vehicleId));
        vehicleRepository.delete(existingVehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Override
    public void addMultiple(List<Vehicle> vehicles) {
        vehicleRepository.saveAll(vehicles);
    }

}
