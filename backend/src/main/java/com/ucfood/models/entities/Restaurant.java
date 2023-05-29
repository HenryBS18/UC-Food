package com.ucfood.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @NotEmpty(message = "Password is required")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "Restaurant Name is required")
    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_description")
    private String restaurantDescription;

    @Lob
    @Column(name = "restaurant_profile_picture", length = 25000000, nullable = true)
    private byte[] restaurantProfilePicture;

    public Restaurant(int restaurantID, String ownerName, String email, String phoneNumber, String password,
            String restaurantName, String restaurantDescription, byte[] restaurantProfilePicture) {
        this.restaurantID = restaurantID;
        this.ownerName = ownerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.restaurantProfilePicture = restaurantProfilePicture;
    }

}
