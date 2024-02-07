package com.ucfood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucfood.models.entities.Menu;
import com.ucfood.models.entities.Transaction;
import com.ucfood.models.entities.TransactionItems;
import com.ucfood.models.repositories.TransactionItemRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionItemService {

    @Autowired
    TransactionItemRepo transactionItemRepo;

    public TransactionItems createTransactionItem(Transaction transaction, Menu menu, int quantity) {
        TransactionItems transactionItems = new TransactionItems(0, transaction, menu, quantity);

        return transactionItemRepo.save(transactionItems);
    }

    public List<TransactionItems> getTransactionItemsByTransaction(Transaction transaction) {
        return transactionItemRepo.findByTransaction(transaction);
    }
}
