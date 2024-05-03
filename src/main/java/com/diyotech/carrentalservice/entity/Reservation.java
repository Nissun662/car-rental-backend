package com.diyotech.carrentalservice.entity;

import com.diyotech.carrentalservice.entity.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private LocalDateTime reservationStartDate;

    private Integer noOfDays;

    private LocalDateTime reservationEndDate;

    private Double subTotal;

    private Double tax;

    private Double totalPrice;

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus status;



    // Multiple reservation can belong to one customer
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Multiple vehicle can be associated with one reservation
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicel_id")
    private Vehicle vehicle;

    // One reservation has one payment
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
