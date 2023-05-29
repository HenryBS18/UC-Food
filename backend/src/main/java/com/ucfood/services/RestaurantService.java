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

    public Restaurant createRestaurant(Restaurant restaurant) {
        Optional<Restaurant> email = restaurantRepo.findByEmail(restaurant.getEmail());

        if (email.isPresent()) {
            return null;
        }

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
