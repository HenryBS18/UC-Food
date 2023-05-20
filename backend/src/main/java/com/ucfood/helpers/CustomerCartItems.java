package com.ucfood.helpers;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Table(name = "customer_cart_items")
public class CustomerCartItems {

    @Column(name = "cart_id")
    private int cartID;

    @Column(name = "menu_id")
    private int menuID;

    @Column(name = "item_quantity")
    private int ItemQuantity;

    public CustomerCartItems(int cartID, int menuID, int itemQuantity) {
        this.cartID = cartID;
        this.menuID = menuID;
        ItemQuantity = itemQuantity;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getItemQuantity() {
        return ItemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        ItemQuantity = itemQuantity;
    }

}
