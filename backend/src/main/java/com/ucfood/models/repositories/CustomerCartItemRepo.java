package com.ucfood.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ucfood.models.entities.CustomerCart;
import com.ucfood.models.entities.CustomerCartItem;
import com.ucfood.models.entities.Menu;

public interface CustomerCartItemRepo extends CrudRepository<CustomerCartItem, Integer> {

    List<CustomerCartItem> findByCustomerCart(CustomerCart customerCart);

    Optional<CustomerCartItem> findByCustomerCartAndMenu(CustomerCart customerCart, Menu menu);
}
