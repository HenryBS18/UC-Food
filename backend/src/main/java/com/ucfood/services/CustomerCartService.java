package com.ucfood.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucfood.models.entities.Customer;
import com.ucfood.models.entities.CustomerCart;
import com.ucfood.models.repositories.CustomerCartRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerCartService {

    @Autowired
    CustomerCartRepo customerCartRepo;

    public CustomerCart createCustomerCart(Customer customer) {
        CustomerCart cart = new CustomerCart(0, customer);

        return customerCartRepo.save(cart);
    }

    public Optional<CustomerCart> getCutomerCartByID(int id) {
        return customerCartRepo.findById(id);
    }
}
