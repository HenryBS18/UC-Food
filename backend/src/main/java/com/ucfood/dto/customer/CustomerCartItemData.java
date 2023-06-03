package com.ucfood.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerCartItemData {

    private String menuName;

    private int itemQuantity;

    public CustomerCartItemData(String menuName, int itemQuantity) {
        this.menuName = menuName;
        this.itemQuantity = itemQuantity;
    }

}
