package com.ucfood.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ucfood.models.entities.Menu;
import com.ucfood.models.entities.Restaurant;

import java.util.List;
import java.util.Optional;

public interface MenuRepo extends CrudRepository<Menu, Integer> {

    Optional<Menu> findByMenuID(int menuID);

    List<Menu> findByRestaurant(Restaurant restaurant);
}
