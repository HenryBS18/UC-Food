package com.ucfood.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucfood.dto.ResponseData;
import com.ucfood.dto.customer.CustomerData;
import com.ucfood.dto.customer.CustomerListData;
import com.ucfood.models.entities.Customer;
import com.ucfood.services.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<CustomerListData>> getCustomerByID(@PathVariable("id") int id) {
        ResponseData<CustomerListData> responseData = new ResponseData<>();
        List<CustomerData> result = new ArrayList<>();

        Optional<Customer> customer = customerService.getCustomerByID(id);

        if (!customer.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Customer not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        result.add(new CustomerData(
                customer.get().getCustomerID(),
                customer.get().getName(),
                customer.get().getEmail(),
                customer.get().getPhoneNumber()));

        responseData.setStatus(true);
        responseData.setPayload(new CustomerListData(result));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseData<CustomerListData>> getAllCustomer() {
        ResponseData<CustomerListData> responseData = new ResponseData<>();
        Iterable<Customer> allCustomer = customerService.getAllCustomer();
        List<CustomerData> result = new ArrayList<>();

        for (Customer customer : allCustomer) {
            result.add(new CustomerData(
                    customer.getCustomerID(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getPhoneNumber()));
        }

        responseData.setStatus(true);
        responseData.setPayload(new CustomerListData(result));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<CustomerListData>> updateCustomer(
            @PathVariable("id") int customerID,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phoneNumber) {
        ResponseData<CustomerListData> responseData = new ResponseData<>();
        List<CustomerData> result = new ArrayList<>();
        Optional<Customer> customer = customerService.getCustomerByID(customerID);

        if (!customer.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Customer not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        Customer customerNew = new Customer(
                customerID,
                name,
                email,
                phoneNumber,
                customer.get().getPassword());

        result.add(new CustomerData(
                customerID,
                name,
                email,
                phoneNumber));

        customerService.updateCustomer(customerNew);
        responseData.setStatus(true);
        responseData.setPayload(new CustomerListData(result));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Customer>> delete(@PathVariable("id") int id) {
        ResponseData<Customer> responseData = new ResponseData<>();

        Optional<Customer> customer = customerService.getCustomerByID(id);

        if (!customer.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Customer not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        customerService.deleteCustomerByID(id);
        responseData.setStatus(true);
        responseData.getMessages().add("Customer Deleted");

        return ResponseEntity.ok(responseData);
    }
}
