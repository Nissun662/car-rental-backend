package com.diyotech.carrentalservice.controller;

import com.diyotech.carrentalservice.entity.Reservation;
import com.diyotech.carrentalservice.exception.CustomerNotFoundException;
import com.diyotech.carrentalservice.exception.ReservationNotFoundException;
import com.diyotech.carrentalservice.exception.VehicleNotAvailableException;
import com.diyotech.carrentalservice.exception.VehicleNotFoundException;
import com.diyotech.carrentalservice.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    // 1. API to create reservation
    // URL = localhost:8080/api/v1/reservations

    /* Json = {
        "customer": {
        "cusId": 3
        },
        "vehicle": {
        "vehicleId": 1
        },
        "noOfDays": 6
    }*/
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) throws CustomerNotFoundException, VehicleNotFoundException, VehicleNotAvailableException {
        reservationService.createReservation(reservation);
        return new ResponseEntity<>("Reservation created successfully.", HttpStatus.CREATED);

    }

    // 2. API to get reservation details
    // URL = http://localhost:8080/api/v1/reservations
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable("id") Long id){
        Reservation reservation = reservationService.getReservation(id);
        return ResponseEntity.ok(reservation);
    }

    // 3. API to delete reservation
    // URL = http://localhost:8080/api/v1/reservations
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") Long id) throws ReservationNotFoundException {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>("Reservation deleted successfully.", HttpStatus.OK);
    }

    // 4. API to update existing reservation by id
    // URL = http://localhost:8080/api/v1/reservations
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservation) throws ReservationNotFoundException {
        reservationService.updateReservation(id, reservation);
        return new ResponseEntity<>("Reservation updated successfully.", HttpStatus.OK);
    }


}
