package com.ucfood.dto.restaurant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantData {

    private int restaurantID;

    private String ownerName;

    private String email;

    private String phoneNumber;

    private String restaurantName;

    private String restaurantDescription;

    public RestaurantData(int restaurantID, String ownerName, String email, String phoneNumber, String restaurantName,
            String restaurantDescription) {
        this.restaurantID = restaurantID;
        this.ownerName = ownerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
    }

}
