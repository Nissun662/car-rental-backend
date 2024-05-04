package com.diyotech.carrentalservice.model;

import com.diyotech.carrentalservice.entity.enums.UserRole;

import lombok.Data;

@Data
public class User {

    private String userName;

    private String password;

    private UserRole role;

    private Long userId;

    public User() {
    }

    public User(String userName, String password, UserRole role, Long userId) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.userId = userId;
    }
}
