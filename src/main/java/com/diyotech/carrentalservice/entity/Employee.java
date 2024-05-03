package com.diyotech.carrentalservice.entity;

import com.diyotech.carrentalservice.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    private String fullName;

    private String email;

    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.EMPLOYEE;

    // One employee can have multiple customers
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<Customer> customers;
}
