package com.ucfood.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionID;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "transaction_status")
    private String transactionStatus;

    @Column(name = "transaction_datetime")
    private LocalDateTime transactionDateTime;

    public Transaction(int transactionID, Restaurant restaurant, Customer customer, String transactionStatus,
            LocalDateTime transactionDateTime) {
        this.transactionID = transactionID;
        this.restaurant = restaurant;
        this.customer = customer;
        this.transactionStatus = transactionStatus;
        this.transactionDateTime = transactionDateTime;
    }

}
