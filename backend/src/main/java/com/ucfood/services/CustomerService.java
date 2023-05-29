package com.ucfood.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucfood.models.entities.Customer;
import com.ucfood.models.repositories.CustomerRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer createCustomer(Customer customer) {
        Optional<Customer> email = customerRepo.findByEmail(customer.getEmail());

        if (email.isPresent()) {
            return null;
        }

        return customerRepo.save(customer);
    }

    public Customer getCustomerByID(int id) {
        return customerRepo.findById(id).get();
    }

    public Iterable<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public void deleteCustomerByID(int id) {
        customerRepo.deleteById(id);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepo.findByEmail(email);
    }
}
