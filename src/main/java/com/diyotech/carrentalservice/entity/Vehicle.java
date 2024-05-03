package com.diyotech.carrentalservice.entity;

import com.diyotech.carrentalservice.entity.enums.VehicleAvailability;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    private String make;

    private String model;

    private String year;

    private String vehicleType;

    private Long mileage;

    private double pricePerDay;


    @Enumerated(value = EnumType.STRING)
    private VehicleAvailability status = VehicleAvailability.AVAILABLE;

    // One vehicle can be associated with multiple reservations
    @JsonIgnore
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

}
