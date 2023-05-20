package com.ucfood.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_cart")
public class CustomerCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_cart_id")
    private int customerCartID;

    @Column(name = "customer_id")
    private int customerID;

    public CustomerCart(int customerCartID, int customerID) {
        this.customerCartID = customerCartID;
        this.customerID = customerID;
    }

    public int getCustomerCartID() {
        return customerCartID;
    }

    public void setCustomerCartID(int customerCartID) {
        this.customerCartID = customerCartID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

}
