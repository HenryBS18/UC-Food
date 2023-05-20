package com.ucfood.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionID;

    @Column(name = "restaurant_id")
    private int restaurantID;

    @Column(name = "customer_id")
    private int customerID;

    @Column(name = "transaction_status")
    private String transactionStatus;

    @Column(name = "transaction_datetime")
    private LocalDateTime transactionDateTime;

    public Transaction(int transactionID, int restaurantID, int customerID, String transactionStatus,
            LocalDateTime transactionDateTime) {
        this.transactionID = transactionID;
        this.restaurantID = restaurantID;
        this.customerID = customerID;
        this.transactionStatus = transactionStatus;
        this.transactionDateTime = transactionDateTime;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

}
