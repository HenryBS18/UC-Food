package com.ucfood.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ucfood.dto.ResponseData;
import com.ucfood.dto.customer.CustomerData;
import com.ucfood.dto.customer.CustomerListData;
import com.ucfood.dto.restaurant.RestaurantData;
import com.ucfood.dto.restaurant.RestaurantListData;
import com.ucfood.models.entities.Customer;
import com.ucfood.models.entities.Restaurant;
import com.ucfood.services.CustomerCartService;
import com.ucfood.services.CustomerService;
import com.ucfood.services.RestaurantService;
import com.ucfood.utils.ImageConverter;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerCartService customerCartService;

    @PostMapping("/restaurant")
    public ResponseEntity<ResponseData<RestaurantListData>> createRestaurant(
            @RequestParam("ownerName") String ownerName,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("password") String password,
            @RequestParam("restaurantName") String restaurantName,
            @RequestParam("restaurantDescription") String restaurantDescription,
            @RequestParam(name = "restaurantProfilePicture", required = false) MultipartFile restaurantProfilePicture) {

        ResponseData<RestaurantListData> responseData = new ResponseData<>();
        List<RestaurantData> result = new ArrayList<>();

        byte[] restaurantProfilePictureByte = null;

        if (restaurantProfilePicture != null) {
            try {
                restaurantProfilePictureByte = ImageConverter.compressImage(restaurantProfilePicture.getBytes());
            } catch (IOException e) {
            }
        }

        Restaurant restaurant = restaurantService.createRestaurant(
                ownerName,
                email,
                phoneNumber,
                password,
                restaurantName,
                restaurantDescription,
                restaurantProfilePictureByte);

        if (restaurant == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Email '" + email + "' Already Used");

            return ResponseEntity.badRequest().body(responseData);
        }

        result.add(new RestaurantData(
                restaurant.getRestaurantID(),
                restaurant.getOwnerName(),
                restaurant.getEmail(),
                restaurant.getPhoneNumber(),
                restaurant.getRestaurantName(),
                restaurant.getRestaurantDescription()));

        responseData.setStatus(true);
        responseData.getMessages().add("Create Account Success");
        responseData.setPayload(new RestaurantListData(result));

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/customer")
    public ResponseEntity<ResponseData<CustomerListData>> createCustomer(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String password) {
        ResponseData<CustomerListData> responseData = new ResponseData<>();
        List<CustomerData> result = new ArrayList<>();

        Customer customerCheck = customerService.createCustomer(
                name,
                email,
                phoneNumber,
                password);

        if (customerCheck == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Email '" + email + "' Already Used");

            return ResponseEntity.badRequest().body(responseData);
        }

        result.add(new CustomerData(
                customerCheck.getCustomerID(),
                customerCheck.getName(),
                customerCheck.getEmail(),
                customerCheck.getPhoneNumber()));

        // Creata Customer Cart
        customerCartService.createCustomerCart(customerCheck);

        responseData.setStatus(true);
        responseData.getMessages().add("Create Account Success");
        responseData.setPayload(new CustomerListData(result));

        return ResponseEntity.ok(responseData);
    }
}
