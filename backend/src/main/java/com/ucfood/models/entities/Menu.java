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
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menu")
@Getter
@Setter
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuID;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @NotEmpty(message = "Menu Name is required")
    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int price;

    @Column(name = "menu_stock")
    private int stock;

    @Lob
    @Column(name = "menu_image", length = 25000000, nullable = true)
    private byte[] menuImage;

    public Menu(int menuID, Restaurant restaurant, String menuName, int price, int stock, byte[] menuImage) {
        this.menuID = menuID;
        this.restaurant = restaurant;
        this.menuName = menuName;
        this.price = price;
        this.stock = stock;
        this.menuImage = menuImage;
    }

}
