package com.diyotech.carrentalservice.controller;

import com.diyotech.carrentalservice.entity.enums.UserRole;
import com.diyotech.carrentalservice.model.User;
import com.diyotech.carrentalservice.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/user/logins")
@AllArgsConstructor
public class UserLoginController {

    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<User> checkValidCustomer(@RequestBody User user) {
        //System.err.println("checking valid user");
        UserRole role = userService.checkValid(user);
        user.setRole(role);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }





}
