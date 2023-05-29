package com.ucfood.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.ucfood.services.CustomerService;
import com.ucfood.services.RestaurantService;
import com.ucfood.utils.ImageConverter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    CustomerService customerService;

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

        try {
            byte[] restaurantProfilePictureByte = null;

            if (restaurantProfilePicture != null) {
                restaurantProfilePictureByte = ImageConverter.compressImage(restaurantProfilePicture.getBytes());
            }

            Restaurant restaurant = new Restaurant(0,
                    ownerName,
                    email,
                    phoneNumber,
                    password,
                    restaurantName,
                    restaurantDescription,
                    restaurantProfilePictureByte);

            Restaurant restaurantCheck = restaurantService.createRestaurant(restaurant);

            if (restaurantCheck == null) {
                responseData.setStatus(false);
                responseData.getMessages().add("Email '" + restaurant.getEmail() + "' Already Used");

                return ResponseEntity.badRequest().body(responseData);
            }

            result.add(new RestaurantData(
                    restaurantCheck.getRestaurantID(),
                    restaurantCheck.getOwnerName(),
                    restaurantCheck.getEmail(),
                    restaurantCheck.getPhoneNumber(),
                    restaurantCheck.getRestaurantName(),
                    restaurantCheck.getRestaurantDescription()));

            responseData.setStatus(true);
            responseData.getMessages().add("Create Account Success");
            responseData.setPayload(new RestaurantListData(result));

            return ResponseEntity.ok(responseData);
        } catch (IOException e) {
            responseData.setStatus(false);
            responseData.getMessages().add("Create Account Failed");

            return ResponseEntity.badRequest().body(responseData);
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<ResponseData<CustomerListData>> createCustomer(
            @Valid @RequestBody Customer customer,
            Errors errors) {

        ResponseData<CustomerListData> responseData = new ResponseData<>();
        List<CustomerData> result = new ArrayList<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);

            return ResponseEntity.badRequest().body(responseData);
        }

        Customer customerCheck = customerService.createCustomer(customer);

        if (customerCheck == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Email '" + customer.getEmail() + "' Already Used");

            return ResponseEntity.badRequest().body(responseData);
        }

        result.add(new CustomerData(
                customerCheck.getCustomerID(),
                customerCheck.getName(),
                customerCheck.getEmail(),
                customerCheck.getPhoneNumber()));

        responseData.setStatus(true);
        responseData.getMessages().add("Create Account Success");
        responseData.setPayload(new CustomerListData(result));

        return ResponseEntity.ok(responseData);
    }
}
