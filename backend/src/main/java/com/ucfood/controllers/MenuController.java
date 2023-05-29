package com.ucfood.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucfood.models.entities.Menu;
import com.ucfood.services.MenuService;

@RestController
@RequestMapping("/api/restaurant/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    }

    @GetMapping("/{id}")
    public Menu getMenuByID(@PathVariable("id") int id) {
        return menuService.getMenuByID(id).get();
    }

    @GetMapping("/all")
    public Iterable<Menu> getAllMenu() {
        return menuService.getAllMenu();
    }

    @PutMapping
    public Menu updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{id}")
    public String deleteMenuByID(@PathVariable("id") int id) {
        menuService.deleteMenuByID(id);
        return "deleted";
    }
}
