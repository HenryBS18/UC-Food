package com.ucfood.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ucfood.models.entities.Restaurant;

public interface RestaurantRepo extends CrudRepository<Restaurant, Integer> {

    Optional<Restaurant> findByRestaurantID(int id);

    Optional<Restaurant> findByEmail(String email);
}
