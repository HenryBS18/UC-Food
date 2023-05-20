package com.ucfood.helpers;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Table(name = "transaction_items")
public class TransactionItems {

    @Column(name = "transaction_id")
    private int transactionID;

    @Column(name = "menu_id")
    private int menuID;

    @Column(name = "item_quantity")
    private int ItemQuantity;

    public TransactionItems(int transactionID, int menuID, int itemQuantity) {
        this.transactionID = transactionID;
        this.menuID = menuID;
        ItemQuantity = itemQuantity;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
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
