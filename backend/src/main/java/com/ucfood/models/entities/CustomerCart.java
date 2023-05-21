package com.ucfood.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_cart")
public class CustomerCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_cart_id")
    private int customerCartID;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public CustomerCart(int customerCartID, Customer customer) {
        this.customerCartID = customerCartID;
        this.customer = customer;
    }

    public int getCustomerCartID() {
        return customerCartID;
    }

    public void setCustomerCartID(int customerCartID) {
        this.customerCartID = customerCartID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
