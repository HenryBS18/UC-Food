package com.ucfood.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ucfood.models.entities.Menu;

public interface MenuRepo extends CrudRepository<Menu, Integer> {
    
}
