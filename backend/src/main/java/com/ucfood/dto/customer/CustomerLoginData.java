package com.ucfood.dto.customer;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerLoginData {

    private boolean login;

    private List<CustomerData> customer = new ArrayList<>();

    public CustomerLoginData(boolean login, List<CustomerData> customer) {
        this.login = login;
        this.customer = customer;
    }

}
