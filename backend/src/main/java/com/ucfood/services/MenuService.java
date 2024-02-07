package com.ucfood.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucfood.models.entities.Menu;
import com.ucfood.models.entities.Restaurant;
import com.ucfood.models.repositories.MenuRepo;
import com.ucfood.utils.ImageConverter;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuRepo menuRepo;

    public Menu createMenu(Restaurant restaurant, String menuName, int price, int stock, byte[] menuImage) {
        Menu menu = new Menu(
                0,
                restaurant,
                menuName,
                price,
                stock,
                menuImage);

        return menuRepo.save(menu);
    }

    public Optional<Menu> getMenuByID(int menuID) {
        return menuRepo.findById(menuID);
    }

    public List<Menu> getAllMenuByRestaurant(Restaurant restaurant) {
        return menuRepo.findByRestaurant(restaurant);
    }

    public Menu updateMenu(int menuID, Restaurant restaurant, String menuName, int price, int stock, byte[] menuImage) {
        Menu menu = new Menu(
                menuID,
                restaurant,
                menuName,
                price,
                stock,
                menuImage);

        return menuRepo.save(menu);
    }

    public void deleteMenuByID(int id) {
        menuRepo.deleteById(id);
    }

    public byte[] getMenuImageByID(int id) {
        Optional<Menu> menu = menuRepo.findByMenuID(id);
        byte[] picture = menu.get().getMenuImage();
        byte[] decompressedPicture = null;

        if (picture != null) {
            decompressedPicture = ImageConverter.decompressImage(picture);
        }
        return decompressedPicture;
    }
}