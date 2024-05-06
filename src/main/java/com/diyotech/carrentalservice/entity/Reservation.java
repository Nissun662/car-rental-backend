package com.diyotech.carrentalservice.entity;

import com.diyotech.carrentalservice.entity.enums.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate reservationStartDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate reservationEndDate;

    private Integer noOfDays;

    private Double subTotal;

    private Double tax;

    private Double totalPrice;

    //private Long vehicleId;

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus status;

    // Multiple reservation can belong to one customer
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // One reservation is associated with one vehicle
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_vehicle_id")
    private Vehicle vehicle;

    // One reservation has one payment
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Payment payment;


}
