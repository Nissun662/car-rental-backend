package com.diyotech.carrentalservice.controller;

import com.diyotech.carrentalservice.entity.Customer;
import com.diyotech.carrentalservice.entity.enums.UserRole;
import com.diyotech.carrentalservice.exception.CustomerNotFoundException;
import com.diyotech.carrentalservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // 1. API to store customer details in DB
    // URL = http://localhost:8080/api/v1/customers
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        customer.setRole(UserRole.CUSTOMER);
        customerService.createCustomer(customer);
        return new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED);
    }

    // 2. API to update customer details
    // URL = http://localhost:8080/api/v1/customers
    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }

    // 3. API to update customer details
    // URL = http://localhost:8080/api/v1/customers
    @PatchMapping("/{id}")
    public ResponseEntity<String > updateDetails(@PathVariable("id") Long id, @RequestBody Customer customer) throws CustomerNotFoundException {
        customerService.updateCustomer(id, customer);
        return new ResponseEntity<>("Customer details updated successfully", HttpStatus.OK);
    }

    // 4. API to delete customer
    // URL = http://localhost:8080/api/v1/customers
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }

//    // 5. API to check valid user
//    // URL = http://localhost:8080/api/v1/customers/login
//    @PostMapping("/customer/login")
//    public ResponseEntity<?> checkValidCustomer (@RequestBody Customer customer){
//
//        Customer authenticatedCustomer = customerService.authenticateCustomer(customer);
//        if(authenticatedCustomer != null && authenticatedCustomer.getRole() == UserRole.CUSTOMER){
//            return ResponseEntity.ok(authenticatedCustomer);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//    }


    // API for self use to add multiple customer for testing purpose
    // URL = http://localhost:8080/api/v1/customers
    @PostMapping("/add-multiple")
    public ResponseEntity<String> addMultiple(@RequestBody List<Customer> customerList) {
        customerService.addMultiple(customerList);
        return new ResponseEntity<>("Multiple Customers added successfully", HttpStatus.CREATED);
    }

}
