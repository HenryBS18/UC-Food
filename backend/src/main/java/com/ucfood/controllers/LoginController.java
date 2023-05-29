package com.ucfood.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucfood.dto.ResponseData;
import com.ucfood.dto.customer.CustomerData;
import com.ucfood.dto.customer.CustomerLoginData;
import com.ucfood.dto.restaurant.RestaurantData;
import com.ucfood.dto.restaurant.RestaurantLoginData;
import com.ucfood.models.entities.Customer;
import com.ucfood.models.entities.Restaurant;
import com.ucfood.services.CustomerService;
import com.ucfood.services.RestaurantService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<ResponseData<CustomerLoginData>> loginCustomer(
            @RequestParam String email,
            @RequestParam String password) {

        ResponseData<CustomerLoginData> responseData = new ResponseData<>();

        Optional<Customer> customer = customerService.getCustomerByEmail(email);

        if (customer.isPresent()) {
            if (password.equals(customer.get().getPassword())) {

                CustomerData customerData = new CustomerData(
                        customer.get().getCustomerID(),
                        customer.get().getName(),
                        customer.get().getEmail(),
                        customer.get().getPhoneNumber());

                List<CustomerData> customerList = new ArrayList<>();
                customerList.add(customerData);

                responseData.setStatus(true);
                responseData.getMessages().add("Login Success");
                responseData.setPayload(new CustomerLoginData(true, customerList));

                return ResponseEntity.ok(responseData);
            } else {
                List<CustomerData> customerList = new ArrayList<>();

                responseData.getMessages().add("Wrong password");
                responseData.setPayload(new CustomerLoginData(false, customerList));

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
            }
        }
        List<CustomerData> customerList = new ArrayList<>();

        responseData.setStatus(false);
        responseData.getMessages().add("Email is invalid");
        responseData.setPayload(new CustomerLoginData(false, customerList));

        return ResponseEntity.badRequest().body(responseData);
    }

    @PostMapping("/restaurant")
    public ResponseEntity<ResponseData<RestaurantLoginData>> loginRestaurant(
            @RequestParam String email,
            @RequestParam String password) {

        ResponseData<RestaurantLoginData> responseData = new ResponseData<>();

        Optional<Restaurant> restaurant = restaurantService.getRestaurantByEmail(email);

        if (restaurant.isPresent()) {
            if (password.equals(restaurant.get().getPassword())) {

                RestaurantData restaurantData = new RestaurantData(
                        restaurant.get().getRestaurantID(),
                        restaurant.get().getOwnerName(),
                        restaurant.get().getEmail(),
                        restaurant.get().getPhoneNumber(),
                        restaurant.get().getRestaurantName(),
                        restaurant.get().getRestaurantDescription());

                List<RestaurantData> restaurantList = new ArrayList<>();
                restaurantList.add(restaurantData);

                responseData.setStatus(true);
                responseData.getMessages().add("Login Success");
                responseData.setPayload(new RestaurantLoginData(true, restaurantList));

                return ResponseEntity.ok(responseData);
            } else {
                List<RestaurantData> restaurantList = new ArrayList<>();

                responseData.getMessages().add("Wrong password");
                responseData.setPayload(new RestaurantLoginData(false, restaurantList));

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
            }
        }
        List<RestaurantData> restaurantList = new ArrayList<>();

        responseData.setStatus(false);
        responseData.getMessages().add("Email is invalid");
        responseData.setPayload(new RestaurantLoginData(false, restaurantList));

        return ResponseEntity.badRequest().body(responseData);
    }
}
