package com.diyotech.carrentalservice.repository;

import com.diyotech.carrentalservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    // Native query to get customer's username and password
    Customer findCustomerByUserNameAndPassword(String username, String password);

    // Native query to get employee username and password
//    Employee findEmployeeByUsername(String username);
//    Employee findEmployeeByPassword(String password);

}
