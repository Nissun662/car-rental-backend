package com.diyotech.carrentalservice.service;

import com.diyotech.carrentalservice.entity.Customer;
import com.diyotech.carrentalservice.exception.CustomerNotFoundException;


import java.util.List;

public interface CustomerService {
    void createCustomer(Customer customer);

    Customer findById(Long id) throws CustomerNotFoundException;

    void updateCustomer(Long id, Customer customer) throws CustomerNotFoundException;

    void deleteCustomer(Long id) throws CustomerNotFoundException;

    Customer authenticateCustomer(Customer customer);

    void addMultiple (List<Customer> customerList);

}
