package com.ucfood.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerCartItemData {

    private int customerCartItemID;

    private int menuID;

    private String menuName;

    private int price;

    private int itemQuantity;

    public CustomerCartItemData(int customerCartItemID, int menuID, String menuName, int price, int itemQuantity) {
        this.customerCartItemID = customerCartItemID;
        this.menuID = menuID;
        this.menuName = menuName;
        this.price = price;
        this.itemQuantity = itemQuantity;
    }

}
