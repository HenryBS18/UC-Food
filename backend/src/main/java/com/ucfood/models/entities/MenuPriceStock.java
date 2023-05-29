package com.ucfood.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menu_price_stock")
@Getter
@Setter
@NoArgsConstructor
public class MenuPriceStock {

    @Id
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "price")
    private int price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    public MenuPriceStock(Menu menu, int price, int stockQuantity) {
        this.menu = menu;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

}
