package com.diyotech.carrentalservice.service.Impl;

import com.diyotech.carrentalservice.entity.Customer;
import com.diyotech.carrentalservice.entity.Employee;
import com.diyotech.carrentalservice.entity.enums.UserRole;
import com.diyotech.carrentalservice.model.User;
import com.diyotech.carrentalservice.model.UserCredentials;
import com.diyotech.carrentalservice.repository.CustomerRepository;
import com.diyotech.carrentalservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

        public Object checkValid(UserCredentials userCredentials) {
            Customer customer = customerRepository.findCustomerByUserNameAndPassword(userCredentials.getUserName(), userCredentials.getPassword());
            if (customer != null) {
                return customer;
            }
            Employee employee = employeeRepository.findEmployeeByUserNameAndPassword(userCredentials.getUserName(), userCredentials.getPassword());
            if (employee != null) {
                return employee;
            }

            return null; // Or throw an exception if needed
        }
}

