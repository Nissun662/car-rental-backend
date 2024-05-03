package com.diyotech.carrentalservice.model;

import com.diyotech.carrentalservice.entity.enums.UserRole;
import lombok.Data;

@Data
public class User {

    private String userName;

    private String password;

    private UserRole role;
}
