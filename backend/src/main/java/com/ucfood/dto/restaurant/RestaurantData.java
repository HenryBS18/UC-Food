package com.ucfood.dto.restaurant;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantData {

    @Column(name = "restaurant_id")
    private int restaurantID;

    @NotEmpty(message = "Owner Name is required")
    @Column(name = "owner_name")
    private String ownerName;

    @NotEmpty(message = "Email is required")
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty(message = "Phone Number is required")
    @Column(name = "phone_number", length = 13)
    private String phoneNumber;

    @NotEmpty(message = "Restaurant Name is required")
    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_description")
    private String restaurantDescription;

    public RestaurantData(int restaurantID, @NotEmpty(message = "Owner Name is required") String ownerName,
            @NotEmpty(message = "Email is required") String email,
            @NotEmpty(message = "Phone Number is required") String phoneNumber,
            @NotEmpty(message = "Restaurant Name is required") String restaurantName, String restaurantDescription) {
        this.restaurantID = restaurantID;
        this.ownerName = ownerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
    }

}
