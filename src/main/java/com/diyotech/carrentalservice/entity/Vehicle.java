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

    private Double pricePerDay;


    @Enumerated(value = EnumType.STRING)
    private VehicleAvailability status = VehicleAvailability.AVAILABLE;

    // One vehicle can be associated with multiple reservations
    @JsonIgnore
    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Reservation reservation;

}
