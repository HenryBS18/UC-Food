package com.ucfood.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucfood.models.entities.Customer;
import com.ucfood.models.entities.Restaurant;
import com.ucfood.models.entities.Transaction;
import com.ucfood.models.repositories.TransactionRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionService {

    @Autowired
    TransactionRepo transactionRepo;

    public Transaction createTransaction(Restaurant restaurant, Customer customer, String status,
            LocalDateTime dateTime) {
        Transaction transaction = new Transaction(0, restaurant, customer, status, dateTime);

        return transactionRepo.save(transaction);
    }

    public List<Transaction> getAllTransactionByCustomer(Customer customer) {
        return transactionRepo.findByCustomer(customer);
    }

    public List<Transaction> getAllTransactionByRestaurant(Restaurant restaurant) {
        return transactionRepo.findByRestaurant(restaurant);
    }

    public Transaction updateTransaction(Restaurant restaurant, Customer customer, String status,
            LocalDateTime dateTime) {
        Transaction transaction = new Transaction(0, restaurant, customer, status, dateTime);

        return transactionRepo.save(transaction);
    }

}
