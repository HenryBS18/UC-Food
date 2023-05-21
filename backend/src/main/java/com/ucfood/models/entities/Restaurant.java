package com.ucfood.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private int restaurantID;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_description")
    private String restaurantDescription;

    @Lob
    @Column(name = "restaurant_profil_picture")
    private Byte[] restaurantProfilPicture;

    public Restaurant(int restaurantID, String ownerName, String email, String phoneNumber, String password,
            String restaurantName, String restaurantDescription, Byte[] restaurantProfilPicture) {
        this.restaurantID = restaurantID;
        this.ownerName = ownerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.restaurantProfilPicture = restaurantProfilPicture;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public Byte[] getRestaurantProfilPicture() {
        return restaurantProfilPicture;
    }

    public void setRestaurantProfilPicture(Byte[] restaurantProfilPicture) {
        this.restaurantProfilPicture = restaurantProfilPicture;
    }

}
