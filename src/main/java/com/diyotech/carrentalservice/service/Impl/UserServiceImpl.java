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

//    public UserRole checkValid(User user) {
//        Customer customer = customerRepository.findCustomerByUserNameAndPassword(user.getUserName(), user.getPassword());
//        if (customer != null) {
//            return customer.getRole();
//        }
//        Employee employee = employeeRepository.findEmployeeByUserNameAndPassword(user.getUserName(), user.getPassword());
//        if (employee != null) {
//            return employee.getRole();
//        }
//
//        throw new IllegalArgumentException("Invalid username or password");
//    }

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

