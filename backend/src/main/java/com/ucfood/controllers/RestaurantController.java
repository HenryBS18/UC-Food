package com.ucfood.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ucfood.dto.ResponseData;
import com.ucfood.dto.restaurant.RestaurantData;
import com.ucfood.dto.restaurant.RestaurantListData;
import com.ucfood.models.entities.Restaurant;
import com.ucfood.services.RestaurantService;
import com.ucfood.utils.ImageConverter;

@RestController
@RequestMapping("api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{id}/profilepicture")
    public ResponseEntity<?> getRestaurantProfilePicture(@PathVariable("id") int id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantByID(id);

        if (!restaurant.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        byte[] picture = restaurantService.getRestaurantProfilePictureByID(id);

        if (picture == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(picture);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseData<RestaurantListData>> getRestaurants() {
        ResponseData<RestaurantListData> responseData = new ResponseData<>();
        Iterable<Restaurant> allRestaurant = restaurantService.getAllRestaurant();
        List<RestaurantData> result = new ArrayList<>();

        for (Restaurant restaurant : allRestaurant) {
            result.add(new RestaurantData(
                    restaurant.getRestaurantID(),
                    restaurant.getOwnerName(),
                    restaurant.getEmail(),
                    restaurant.getPhoneNumber(),
                    restaurant.getRestaurantName(),
                    restaurant.getRestaurantDescription()));
        }

        responseData.setStatus(true);
        responseData.setPayload(new RestaurantListData(result));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<RestaurantListData>> getRestaurantByID(@PathVariable("id") int id) {
        ResponseData<RestaurantListData> responseData = new ResponseData<>();
        List<RestaurantData> result = new ArrayList<>();

        Optional<Restaurant> restaurantOptional = restaurantService.getRestaurantByID(id);

        if (!restaurantOptional.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Invalid Restaurant ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        Restaurant restaurant = restaurantOptional.get();

        result.add(new RestaurantData(
                restaurant.getRestaurantID(),
                restaurant.getOwnerName(),
                restaurant.getEmail(),
                restaurant.getPhoneNumber(),
                restaurant.getRestaurantName(),
                restaurant.getRestaurantDescription()));

        responseData.setStatus(true);
        responseData.setPayload(new RestaurantListData(result));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<ResponseData<RestaurantListData>> updateRestaurant(
            @PathVariable("id") int restaurantID,
            @RequestParam String ownerName,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String restaurantName,
            @RequestParam String restaurantDescription,
            @RequestParam MultipartFile restaurantProfilePicture) {
        ResponseData<RestaurantListData> responseData = new ResponseData<>();
        List<RestaurantData> result = new ArrayList<>();

        result.add(new RestaurantData(
                restaurantID,
                ownerName,
                email,
                phoneNumber,
                restaurantName,
                restaurantDescription));

        byte[] restaurantProfilePictureByte = null;

        if (restaurantProfilePicture != null) {
            try {
                restaurantProfilePictureByte = ImageConverter.compressImage(restaurantProfilePicture.getBytes());
            } catch (IOException e) {
            }
        }

        Optional<Restaurant> restaurantOld = restaurantService.getRestaurantByID(restaurantID);

        Restaurant restaurantNew = restaurantService.createRestaurant(
                ownerName,
                email,
                phoneNumber,
                restaurantOld.get().getPassword(),
                restaurantName,
                restaurantDescription,
                restaurantProfilePictureByte);

        restaurantService.updateRestaurant(restaurantNew);
        responseData.setStatus(true);
        responseData.setPayload(new RestaurantListData(result));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseData<RestaurantListData>> deleteRestaurant(@PathVariable("id") int id) {
        ResponseData<RestaurantListData> responseData = new ResponseData<>();

        Restaurant restaurant = restaurantService.getRestaurantByID(id).get();

        if (restaurant == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Invalid Restaurant ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        restaurantService.deleteRestaurantByID(id);
        responseData.setStatus(true);
        responseData.getMessages().add("Customer Deleted");
        return ResponseEntity.ok(responseData);
    }

}
