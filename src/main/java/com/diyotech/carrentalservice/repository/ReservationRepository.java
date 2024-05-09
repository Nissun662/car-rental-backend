package com.diyotech.carrentalservice.repository;

import com.diyotech.carrentalservice.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    //Reservation findReservationByCustomer_CusId(Long customerId);
}
