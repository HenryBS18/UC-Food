package com.ucfood.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_cart_items")
@Getter
@Setter
@NoArgsConstructor
public class CustomerCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_cart_item_id")
    private int customerCartItemID;

    @ManyToOne
    @JoinColumn(name = "customer_cart_id")
    private CustomerCart customerCart;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "item_quantity")
    private int ItemQuantity;

    public CustomerCartItem(int customerCartItemID, CustomerCart customerCart, Menu menu, int itemQuantity) {
        this.customerCartItemID = customerCartItemID;
        this.customerCart = customerCart;
        this.menu = menu;
        ItemQuantity = itemQuantity;
    }

}
