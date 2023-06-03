package com.ucfood.dto.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuData {

    private int menuID;

    private String menuName;

    private int price;

    private int stock;

    public MenuData(int menuID, String menuName, int price, int stock) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.price = price;
        this.stock = stock;
    }

}
