package com.ucfood.helpers;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Table(name = "menu_price_stock")
public class MenuPriceStock {

    @Column(name = "menu_id")
    private int menuID;

    @Column(name = "price")
    private int price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    public MenuPriceStock(int menuID, int price, int stockQuantity) {
        this.menuID = menuID;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

}
