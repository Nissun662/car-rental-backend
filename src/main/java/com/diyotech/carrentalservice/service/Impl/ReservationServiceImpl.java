package com.diyotech.carrentalservice.service.Impl;

import com.diyotech.carrentalservice.entity.*;
import com.diyotech.carrentalservice.entity.enums.ReservationStatus;
import com.diyotech.carrentalservice.entity.enums.VehicleAvailability;
import com.diyotech.carrentalservice.exception.CustomerNotFoundException;
import com.diyotech.carrentalservice.exception.ReservationNotFoundException;
import com.diyotech.carrentalservice.exception.VehicleNotAvailableException;
import com.diyotech.carrentalservice.exception.VehicleNotFoundException;
import com.diyotech.carrentalservice.repository.CustomerRepository;
import com.diyotech.carrentalservice.repository.ReservationRepository;
import com.diyotech.carrentalservice.repository.VehicleRepository;
import com.diyotech.carrentalservice.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    /*1. Method to create reservation by checking if existing customer or not and existing vehicle or not.
    * This method check the vehicel status and denies reservation if vehicle is not available.
    * It accepts the reservation details customerId and vehicleId and noOfDays and calculates the price for the rental */
    @Override
    public void createReservation(Reservation reservation) throws CustomerNotFoundException, VehicleNotFoundException, VehicleNotAvailableException {

        Double subTotal = 0.0;

        Optional<Customer> customerCheck = customerRepository.findById(reservation.getCustomer().getCusId());
        if (customerCheck.isEmpty()) {
            throw new CustomerNotFoundException(" Customer not found for id: "+ reservation.getCustomer().getCusId());
        }

        Optional<Vehicle> vehicleCheck = vehicleRepository.findById(reservation.getVehicle().getVehicleId());
        if (vehicleCheck.isEmpty()) {
            throw new VehicleNotFoundException(" Vehicle not found for id: "+ reservation.getVehicle().getVehicleId());
        }

        Customer customer = customerCheck.get(); // get customer

        Vehicle vehicle = vehicleCheck.get();  // get vehicle

        if (vehicle.getStatus() == VehicleAvailability.NOT_AVAILABLE) {  // ***** checking vehicle availability beforehand *****
            throw new VehicleNotAvailableException("Vehicle is not available for reservation");
        }
        vehicle.setStatus(VehicleAvailability.NOT_AVAILABLE);  // update vehicle to unavailable
        vehicleRepository.save(vehicle); // after update save to DB as this particular vehicle is not available

        Double pricePerDay = vehicle.getPricePerDay(); // get vehicles daily rate

        Reservation reservation1 = new Reservation();
        reservation1.setCustomer(customer);
        reservation1.setVehicle(vehicle);
        reservation1.setReservationStartDate(LocalDateTime.now());


        LocalDateTime endDate = reservation1.getReservationStartDate().plusDays(reservation.getNoOfDays());

        reservation1.setNoOfDays(reservation.getNoOfDays());
        reservation1.setReservationEndDate(endDate);

        reservation1.setStatus(ReservationStatus.CONFIRMED);

        // Price calculations
        subTotal = subTotal + ( pricePerDay * reservation.getNoOfDays());
        Double tax = 0.1 * subTotal;
        Double totalPrice = subTotal + tax;

        reservation1.setTax(tax);
        reservation1.setSubTotal(subTotal);
        reservation1.setTotalPrice(totalPrice);

        Payment processedPayment = processPayment(reservation.getPayment());        // get payment details reservation
        reservation1.setPayment(processedPayment);

        reservationRepository.save(reservation1);
    }

    // 2. Method to process payment
    public Payment processPayment(Payment payment) {
        // assume calling third party API and get confirmation code
        payment.setConfirmationCode("XYZ123");
        return payment;
    }

    // 3. Method to get reservation by id
    @Override
    public Reservation getReservation(Long reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isEmpty()) {
            throw new RuntimeException("Reservation not found for id: "+ reservationId);
        }
        return reservationOptional.get();
    }

    // 4. Method to delete reservation
    @Override
    public void deleteReservation(Long reservationId) throws ReservationNotFoundException {
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new ReservationNotFoundException("Reservation not found for id: "+ reservationId) );

        // Change reservation status to cancelled
        existingReservation.setStatus(ReservationStatus.CANCELLED);

        // update vehicle availability to true when reservation is deleted
        Vehicle vehicle = existingReservation.getVehicle();
        vehicle.setStatus(VehicleAvailability.AVAILABLE);
        vehicleRepository.save(vehicle);

        reservationRepository.delete(existingReservation);
    }

    // 5. Method to update existing reservation
    @Override
    public void updateReservation(Long reservationId, Reservation reservation) throws ReservationNotFoundException {
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new ReservationNotFoundException("Reservation not found for id:"+ reservationId));

        if(reservation.getNoOfDays() != null){
            existingReservation.setNoOfDays(reservation.getNoOfDays());
        }
        if(reservation.getCustomer().getCusId() != null){
            existingReservation.setCustomer(reservation.getCustomer());
        }
        if(reservation.getVehicle().getVehicleId() != null){
            existingReservation.setVehicle(reservation.getVehicle());
        }

        // Update payment method
        if(reservation.getPayment() != null){
            Payment existingPayment = existingReservation.getPayment();
            Payment newPayment = reservation.getPayment();

            if(newPayment.getCreditCardNumber() != null){
                existingPayment.setCreditCardNumber(newPayment.getCreditCardNumber());
            }
            if(newPayment.getCvv() != null){
                existingPayment.setCvv(newPayment.getCvv());
            }
            if(newPayment.getExpiration() != null){
                existingPayment.setExpiration(newPayment.getExpiration());
            }

            reservationRepository.save(existingReservation);
        }
    }

}
