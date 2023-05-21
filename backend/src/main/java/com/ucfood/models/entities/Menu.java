package com.ucfood.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuID;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "menu_name")
    private String menuName;

    @Lob
    @Column(name = "menu_image")
    private Byte[] menuImage;

    public Menu(int menuID, Restaurant restaurant, String menuName, Byte[] menuImage) {
        this.menuID = menuID;
        this.restaurant = restaurant;
        this.menuName = menuName;
        this.menuImage = menuImage;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Byte[] getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(Byte[] menuImage) {
        this.menuImage = menuImage;
    }

}
