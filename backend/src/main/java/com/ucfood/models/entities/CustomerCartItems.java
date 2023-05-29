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
@Table(name = "customer_cart_items")
@Getter
@Setter
@NoArgsConstructor
public class CustomerCartItems {

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_cart_id")
    private CustomerCart customerCart;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "item_quantity")
    private int ItemQuantity;

    public CustomerCartItems(CustomerCart customerCart, Menu menu, int itemQuantity) {
        this.customerCart = customerCart;
        this.menu = menu;
        ItemQuantity = itemQuantity;
    }

}
