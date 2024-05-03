package com.diyotech.carrentalservice.service.Impl;

import com.diyotech.carrentalservice.entity.Employee;
import com.diyotech.carrentalservice.exception.EmployeeNotFoundException;
import com.diyotech.carrentalservice.repository.EmployeeRepository;
import com.diyotech.carrentalservice.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Long id) throws EmployeeNotFoundException {
       Optional<Employee> employeeOptional = employeeRepository.findById(id);
       if (employeeOptional.isEmpty()) {
           throw new EmployeeNotFoundException("Employee with id " + id + " not found");
       }
       return employeeOptional.get();
    }

    @Override
    public void updateEmployee(Long id, Employee employee) throws EmployeeNotFoundException {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee not found with id: "+ id));
        if(employee.getFullName() != null){
            existingEmployee.setFullName(employee.getFullName());
        }
        if(employee.getEmail() != null){
            existingEmployee.setEmail(employee.getEmail());
        }
        if(employee.getUserName() != null){
            existingEmployee.setUserName(employee.getUserName());
        }
        if(employee.getPassword() != null){
            existingEmployee.setPassword(employee.getPassword());
        }

        employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleterEmployee(Long id) throws EmployeeNotFoundException {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Customer not found with id: "+ id));
        employeeRepository.delete(existingEmployee);
    }


    @Override
    public void addMultiple(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }
}
