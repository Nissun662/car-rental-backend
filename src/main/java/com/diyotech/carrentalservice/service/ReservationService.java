package com.diyotech.carrentalservice.service;

import com.diyotech.carrentalservice.entity.Reservation;
import com.diyotech.carrentalservice.exception.CustomerNotFoundException;
import com.diyotech.carrentalservice.exception.ReservationNotFoundException;
import com.diyotech.carrentalservice.exception.VehicleNotAvailableException;
import com.diyotech.carrentalservice.exception.VehicleNotFoundException;

import java.util.List;

public interface ReservationService {

    void createReservation(Reservation reservation) throws CustomerNotFoundException, VehicleNotFoundException, VehicleNotAvailableException;

    Reservation getReservation(Long reservationId);

    void deleteReservation(Long reservationId) throws ReservationNotFoundException;

    void updateReservation(Long reservationId, Reservation reservation) throws ReservationNotFoundException;

    List<Reservation> getAllReservations();



}
