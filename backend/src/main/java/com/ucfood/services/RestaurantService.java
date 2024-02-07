package com.ucfood.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucfood.models.entities.Restaurant;
import com.ucfood.models.repositories.RestaurantRepo;
import com.ucfood.utils.ImageConverter;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    public Restaurant createRestaurant(String ownerName, String email, String phoneNumber, String password,
            String restaurantName, String restaurantDescription, byte[] profilePicture) {
        Optional<Restaurant> emailCheck = restaurantRepo.findByEmail(email);

        if (emailCheck.isPresent()) {
            return null;
        }

        Restaurant restaurant = new Restaurant(
                0,
                ownerName,
                email,
                phoneNumber,
                password,
                restaurantName,
                restaurantDescription,
                profilePicture);

        return restaurantRepo.save(restaurant);
    }

    public Optional<Restaurant> getRestaurantByID(int id) {
        return restaurantRepo.findById(id);
    }

    public Iterable<Restaurant> getAllRestaurant() {
        return restaurantRepo.findAll();
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    public void deleteRestaurantByID(int id) {
        restaurantRepo.deleteById(id);
    }

    public byte[] getRestaurantProfilePictureByID(int id) {
        Optional<Restaurant> restaurant = restaurantRepo.findByRestaurantID(id);
        byte[] picture = restaurant.get().getRestaurantProfilePicture();
        byte[] decompressedPicture = null;

        if (picture != null) {
            decompressedPicture = ImageConverter.decompressImage(picture);
        }
        return decompressedPicture;
    }

    public Optional<Restaurant> getRestaurantByEmail(String email) {
        return restaurantRepo.findByEmail(email);
    }
}
