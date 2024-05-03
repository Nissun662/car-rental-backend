package com.diyotech.carrentalservice.service;

import com.diyotech.carrentalservice.entity.Customer;
import com.diyotech.carrentalservice.entity.Employee;
import com.diyotech.carrentalservice.entity.enums.UserRole;
import com.diyotech.carrentalservice.model.User;
import com.diyotech.carrentalservice.repository.CustomerRepository;
import com.diyotech.carrentalservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserServiceImpl {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    public UserRole checkValid(User user) {
        Customer customer = customerRepository.findCustomerByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (customer != null) {
            return customer.getRole();
        }
        Employee employee = employeeRepository.findEmployeeByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (employee != null) {
            return employee.getRole();
        }

        return user.getRole();
        //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password");
    }
}
