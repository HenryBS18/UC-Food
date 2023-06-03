package com.ucfood.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ucfood.models.entities.CustomerCart;
import com.ucfood.models.entities.CustomerCartItem;

public interface CustomerCartItemRepo extends CrudRepository<CustomerCartItem, Integer> {

    List<CustomerCartItem> findByCustomerCart(CustomerCart customerCart);
}
