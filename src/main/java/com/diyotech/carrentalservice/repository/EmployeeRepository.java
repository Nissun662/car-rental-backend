package com.diyotech.carrentalservice.repository;

import com.diyotech.carrentalservice.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

      Employee findEmployeeByUserNameAndPassword(String userName, String password);
}
