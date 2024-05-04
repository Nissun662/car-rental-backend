package com.diyotech.carrentalservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserCredentials {
    private String userName;
    private String password;

    public UserCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
