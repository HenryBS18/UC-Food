package com.ucfood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucfood.models.entities.CustomerCart;
import com.ucfood.models.entities.CustomerCartItem;
import com.ucfood.models.entities.Menu;
import com.ucfood.models.repositories.CustomerCartItemRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerCartItemService {
    
    @Autowired
    CustomerCartItemRepo customerCartItemRepo;

    public CustomerCartItem createCustomerCartItem(CustomerCart customerCart, Menu menu, int quantity) {
        CustomerCartItem customerCartItem = new CustomerCartItem(0, customerCart, menu, quantity);

        return customerCartItemRepo.save(customerCartItem);
    }

    public List<CustomerCartItem> getCustomerCartItemByCustomerCart(CustomerCart customerCart) {
        List<CustomerCartItem> customerCartItems = customerCartItemRepo.findByCustomerCart(customerCart);

        if (customerCartItems == null) {
            return null;
        }

        return customerCartItemRepo.findByCustomerCart(customerCart);
    }
}
