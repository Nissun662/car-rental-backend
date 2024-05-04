package com.diyotech.carrentalservice.controller;

import com.diyotech.carrentalservice.entity.Customer;
import com.diyotech.carrentalservice.entity.Vehicle;
import com.diyotech.carrentalservice.exception.CustomerNotFoundException;
import com.diyotech.carrentalservice.exception.VehicleNotFoundException;
import com.diyotech.carrentalservice.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService ;

    // 1. API to add vehicle
    // URL = http://localhost:8080/api/v1/vehicles
    @PostMapping
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.saveVehicle(vehicle);
        return new ResponseEntity<>("Vehicle saved successfully", HttpStatus.CREATED);
    }

    // 2. API to get vehicle by id
    // URL = http://localhost:8080/api/v1/vehicles
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleService.getVehicle(id);
        return ResponseEntity.ok(vehicle);
    }

    // 3. API to update vehicle details
    // URL = http://localhost:8080/api/vi/vehicles
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable("id") Long id, @RequestBody Vehicle vehicle) throws VehicleNotFoundException {
        vehicleService.updateVehicle(id, vehicle);
        return new ResponseEntity<>("Vehicle updated successfully", HttpStatus.OK);
    }

    // 4. API to delete vehicle by id
    // URL = http://localhost:8080/api/vi/vehicles/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) throws VehicleNotFoundException {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>("Vehicle deleted successfully", HttpStatus.OK);
    }

    // 5. API to get all vehicles
    // URL = http://localhost:8080/api/vi/vehicles
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
         return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    // API to add multiple vehicles
    // URL = http://localhost:8080/api/vi/vehicles/add-multiple
    @PostMapping("/add-multiple")
    public ResponseEntity<String> addMultipleVehicles(@RequestBody List<Vehicle> vehicles) {
        vehicleService.addMultiple(vehicles);
        return new ResponseEntity<>("Vehicles added successfully", HttpStatus.CREATED);
    }


}
