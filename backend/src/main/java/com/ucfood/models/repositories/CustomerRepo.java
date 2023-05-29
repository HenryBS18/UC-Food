package com.ucfood.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ucfood.models.entities.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);
}
