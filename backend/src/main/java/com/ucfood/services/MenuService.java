package com.ucfood.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucfood.models.entities.Menu;
import com.ucfood.models.repositories.MenuRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuRepo menuRepo;

    public Menu createMenu(Menu menu) {
        return menuRepo.save(menu);
    }

    public Optional<Menu> getMenuByID(int id) {
        return menuRepo.findById(id);
    }

    public Iterable<Menu> getAllMenu() {
        return menuRepo.findAll();
    }

    public Menu updateMenu(Menu menu) {
        return menuRepo.save(menu);
    }

    public void deleteMenuByID(int id) {
        menuRepo.deleteById(id);
    }
}