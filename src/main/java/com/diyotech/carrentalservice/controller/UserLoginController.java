package com.diyotech.carrentalservice.controller;

import com.diyotech.carrentalservice.entity.Customer;
import com.diyotech.carrentalservice.entity.Employee;
import com.diyotech.carrentalservice.entity.enums.UserRole;
import com.diyotech.carrentalservice.model.User;
import com.diyotech.carrentalservice.model.UserCredentials;
import com.diyotech.carrentalservice.service.Impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/user/logins")
@AllArgsConstructor
public class UserLoginController {

    private final UserServiceImpl userService;

    // 1. API to validate username and password and return User(Id, Role etc)
    @PostMapping
    public ResponseEntity<User> checkValidCustomer(@RequestBody User user) {
        UserCredentials userCredentials = new UserCredentials(user.getUserName(), user.getPassword());
        //System.err.println("checking valid user");
        Object result = userService.checkValid(userCredentials);

        if (result instanceof Customer customer) {
            user.setUserId(customer.getCusId());
            user.setRole(customer.getRole());
        } else if (result instanceof Employee employee) {
            user.setUserId(employee.getEmpId());
            user.setRole(employee.getRole());
        } else {
            // Handle the case where no user is found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }










}
