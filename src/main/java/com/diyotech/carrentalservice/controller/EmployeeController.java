package com.diyotech.carrentalservice.controller;


import com.diyotech.carrentalservice.entity.Employee;
import com.diyotech.carrentalservice.entity.enums.UserRole;
import com.diyotech.carrentalservice.exception.EmployeeNotFoundException;
import com.diyotech.carrentalservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // 1. API to store employee details in DB
    // URL = http://localhost:8080/api/v1/employees
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        employee.setRole(UserRole.EMPLOYEE);
        employeeService.createEmployee(employee);
        return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
    }

    // 2. API to update customer details
    // URL = http://localhost:8080/api/v1/employees
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    // 3. API to update customer details
    // URL = http://localhost:8080/api/v1/employees
    @PatchMapping("/{id}")
    public ResponseEntity<String > updateDetails(@PathVariable("id") Long id, @RequestBody Employee employee) throws EmployeeNotFoundException {
        Employee authenticatedEmployee = employeeService.findById(id);
        if(authenticatedEmployee.getRole() != UserRole.EMPLOYEE) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
        employeeService.updateEmployee(id, employee);
        return new ResponseEntity<>("Employee details updated successfully", HttpStatus.OK);
    }

    // 4. API to delete customer
    // URL = http://localhost:8080/api/v1/employees
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        Employee authenticatedEmployee = employeeService.findById(id);
        if(authenticatedEmployee.getRole() != UserRole.EMPLOYEE) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
        employeeService.deleterEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }

    // 5. API to check valid emmployee
    // URL = http://localhost:8080/api/v1/employees/login
    @PostMapping("/employee/login")
    public ResponseEntity<?> checkValidEmployee (@RequestBody Employee employee){
        Employee authenticatedEmployee = employeeService.authenticateEmployee(employee);
        if(authenticatedEmployee != null && authenticatedEmployee.getRole() == UserRole.EMPLOYEE){
            return ResponseEntity.ok(authenticatedEmployee);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // API to add multiple employees
    // URL = http://localhost:8080/api/v1/employees
    @PostMapping("/add-multiple")
    public ResponseEntity<String> addMultipleEmployees(@RequestBody List<Employee> employees){
        employeeService.addMultiple(employees);
        return new ResponseEntity<>("Employees added successfully", HttpStatus.OK);
    }

}
