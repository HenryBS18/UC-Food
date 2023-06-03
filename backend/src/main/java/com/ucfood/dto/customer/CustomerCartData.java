package com.ucfood.dto.customer;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerCartData {

    private int customerCartID;

    private List<CustomerCartItemData> customerCart = new ArrayList<>();

    public CustomerCartData(int customerCartID, List<CustomerCartItemData> customerCart) {
        this.customerCartID = customerCartID;
        this.customerCart = customerCart;
    }

}
