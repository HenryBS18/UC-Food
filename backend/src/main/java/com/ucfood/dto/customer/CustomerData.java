package com.ucfood.dto.customer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerData {

    @Column(name = "customer_id")
    private int customerID;

    @NotEmpty(message = "name is required")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "email is required")
    @Email(message = "email is invalid")
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty(message = "phone number is required")
    @Column(name = "phone_number")
    private String phoneNumber;

    public CustomerData(int customerID, @NotEmpty(message = "name is required") String name,
            @NotEmpty(message = "email is required") @Email(message = "email is invalid") String email,
            @NotEmpty(message = "phone number is required") String phoneNumber) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
