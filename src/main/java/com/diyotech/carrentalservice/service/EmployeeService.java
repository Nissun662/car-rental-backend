package com.diyotech.carrentalservice.service;

import com.diyotech.carrentalservice.entity.Employee;
import com.diyotech.carrentalservice.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    void createEmployee(Employee employee);

    Employee findById(Long id) throws EmployeeNotFoundException;

    void updateEmployee(Long id, Employee employee) throws EmployeeNotFoundException;

    void deleterEmployee(Long id) throws EmployeeNotFoundException;

    void addMultiple (List<Employee> employees);

}
