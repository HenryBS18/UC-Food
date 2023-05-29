package com.ucfood.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

        Customer customer = customerService.getCustomerByID(id);

        if (customer == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Invalid Customer ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        result.add(new CustomerData(customer.getCustomerID(), customer.getName(), customer.getEmail(),
                customer.getPhoneNumber()));

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
            result.add(new CustomerData(customer.getCustomerID(), customer.getName(), customer.getEmail(),
                    customer.getPhoneNumber()));
        }

        responseData.setStatus(true);
        responseData.setPayload(new CustomerListData(result));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<CustomerListData>> updateCustomer(@RequestBody Customer customer) {
        ResponseData<CustomerListData> responseData = new ResponseData<>();
        List<CustomerData> result = new ArrayList<>();

        result.add(new CustomerData(customer.getCustomerID(), customer.getName(), customer.getEmail(),
                customer.getPhoneNumber()));

        customerService.updateCustomer(customer);
        responseData.setStatus(true);
        responseData.setPayload(new CustomerListData(result));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Customer>> delete(@PathVariable("id") int id) {
        ResponseData<Customer> responseData = new ResponseData<>();

        Customer customer = customerService.getCustomerByID(id);

        if (customer == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Invalid Customer ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        customerService.deleteCustomerByID(id);
        responseData.setStatus(true);
        responseData.getMessages().add("Customer Deleted");
        return ResponseEntity.ok(responseData);
    }
}
