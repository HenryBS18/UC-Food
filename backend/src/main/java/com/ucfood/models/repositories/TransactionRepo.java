package com.ucfood.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ucfood.models.entities.Customer;
import com.ucfood.models.entities.Restaurant;
import com.ucfood.models.entities.Transaction;
import java.util.List;


public interface TransactionRepo extends CrudRepository<Transaction, Integer> {
    
    List<Transaction> findByRestaurant(Restaurant restaurant);

    List<Transaction> findByCustomer(Customer customer);
}
