package com.ucfood.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ucfood.models.entities.CustomerCart;


public interface CustomerCartRepo extends CrudRepository<CustomerCart, Integer> {
    
    Optional<CustomerCart>findByCustomerCartID(int customerCartID);
}
