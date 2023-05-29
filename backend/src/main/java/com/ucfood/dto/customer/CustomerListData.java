package com.ucfood.dto.customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerListData {

    private List<?> customer = new ArrayList<>();

    public CustomerListData(List<?> customer) {
        this.customer = customer;
    }

    public List<?> getCustomer() {
        return customer;
    }

    public void setCustomer(List<?> customer) {
        this.customer = customer;
    }

}
