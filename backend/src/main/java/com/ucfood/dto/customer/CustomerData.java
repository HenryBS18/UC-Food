package com.ucfood.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerData {

    private int customerID;

    private String name;

    private String email;

    private String phoneNumber;

    public CustomerData(int customerID, String name, String email, String phoneNumber) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
