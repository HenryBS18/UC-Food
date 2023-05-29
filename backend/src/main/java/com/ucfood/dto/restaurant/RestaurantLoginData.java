package com.ucfood.dto.restaurant;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantLoginData {
    private boolean login;

    private List<RestaurantData> restaurant = new ArrayList<>();

    public RestaurantLoginData(boolean login, List<RestaurantData> restaurant) {
        this.login = login;
        this.restaurant = restaurant;
    }

}
