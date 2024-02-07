package com.ucfood.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucfood.dto.ResponseData;
import com.ucfood.models.entities.Transaction;
import com.ucfood.services.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    
    // @PostMapping("/customer/{id}")
    // public ResponseEntity<ResponseData<Transaction>> createTransaction(
    //     int restaurantID,
    //     int customerID,
    //     int menuID
    // ) {
    //     ResponseData<Transaction> responseData = new ResponseData<>();

        

    //     responseData.setStatus(true);
    //     responseData.setPayload();

    //     return null;
    // }

    @GetMapping("/customer/{id}")
    public ResponseEntity<ResponseData<?>> getAllTransactionByCustomer() {
        return null;
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<ResponseData<?>> getAllTransactionByRestaurant() {
        return null;
    }
}
