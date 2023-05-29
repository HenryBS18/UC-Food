package com.ucfood.dto.restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListData {

    private List<RestaurantData> restaurant = new ArrayList<>();

    public RestaurantListData(List<RestaurantData> restaurant) {
        this.restaurant = restaurant;
    }

    public List<RestaurantData> getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(List<RestaurantData> restaurant) {
        this.restaurant = restaurant;
    }

}
