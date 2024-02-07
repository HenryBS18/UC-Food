package com.ucfood.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ucfood.models.entities.Transaction;
import com.ucfood.models.entities.TransactionItems;
import java.util.List;


public interface TransactionItemRepo extends CrudRepository<TransactionItems, Integer> {
    
    List<TransactionItems> findByTransaction(Transaction transaction);
}
