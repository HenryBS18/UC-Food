package com.ucfood.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuID;

    @Column(name = "restaurant_id")
    private int restaurantID;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_image")
    private String menuImage;

    public Menu(int menuID, int restaurantID, String menuName, String menuImage) {
        this.menuID = menuID;
        this.restaurantID = restaurantID;
        this.menuName = menuName;
        this.menuImage = menuImage;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

}
