package com.ucfood.dto.menu;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuListData {

    private List<MenuData> menu = new ArrayList<>();

    public MenuListData(List<MenuData> menu) {
        this.menu = menu;
    }

}
