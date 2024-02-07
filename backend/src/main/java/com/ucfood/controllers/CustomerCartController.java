package com.ucfood.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucfood.dto.ResponseData;
import com.ucfood.dto.customer.CustomerCartData;
import com.ucfood.dto.customer.CustomerCartItemData;
import com.ucfood.models.entities.Customer;
import com.ucfood.models.entities.CustomerCart;
import com.ucfood.models.entities.CustomerCartItem;
import com.ucfood.models.entities.Menu;
import com.ucfood.services.CustomerCartItemService;
import com.ucfood.services.CustomerCartService;
import com.ucfood.services.CustomerService;
import com.ucfood.services.MenuService;

@RestController
@RequestMapping("/api/customer")
public class CustomerCartController {

    @Autowired
    CustomerCartItemService customerCartItemService;

    @Autowired
    CustomerCartService customerCartService;

    @Autowired
    CustomerService customerService;

    @Autowired
    MenuService menuService;

    @PostMapping("/{id}/cart/add")
    public ResponseEntity<ResponseData<CustomerCartItem>> addItemToCart(
            @PathVariable("id") int customerCartID,
            @RequestParam int menuID,
            @RequestParam int itemQuantity) {

        ResponseData<CustomerCartItem> responseData = new ResponseData<>();
        Optional<CustomerCart> customerCart = customerCartService.getCutomerCartByID(customerCartID);
        Optional<Menu> menu = menuService.getMenuByID(menuID);

        if (!customerCart.isPresent() || !menu.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Failed to add item");

            return ResponseEntity.badRequest().body(responseData);
        }

        customerCartItemService.createCustomerCartItem(customerCart.get(), menu.get(), itemQuantity);

        responseData.setStatus(true);
        responseData.getMessages().add(menu.get().getMenuName() + " added to cart");

        return ResponseEntity.ok(responseData);
    }

    @PutMapping("{id}/cart")
    public ResponseEntity<ResponseData<CustomerCartItem>> updateCartItem(
            @PathVariable("id") int customerCartID,
            @RequestParam int menuID,
            @RequestParam int itemQuantity) {

        ResponseData<CustomerCartItem> responseData = new ResponseData<>();
        Optional<CustomerCart> customerCart = customerCartService.getCutomerCartByID(customerCartID);
        Optional<Menu> menu = menuService.getMenuByID(menuID);

        if (!customerCart.isPresent() || !menu.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Failed to update item");

            return ResponseEntity.badRequest().body(responseData);
        }

        Optional<CustomerCartItem> customerCartItem = customerCartItemService
                .getCustomerCartItemByCustomerCartAndMenu(customerCart.get(), menu.get());

        if (!customerCartItem.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Failed to update item");

            return ResponseEntity.badRequest().body(responseData);
        }

        customerCartItemService.updateCustomerCartItem(
                customerCartItem.get().getCustomerCartItemID(),
                customerCart.get(),
                menu.get(),
                itemQuantity);

        responseData.setStatus(true);
        responseData.getMessages().add(menu.get().getMenuName() + " updated");

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}/cart/all")
    public ResponseEntity<ResponseData<CustomerCartData>> getCustomerCartByID(
            @PathVariable("id") int customerID) {
        ResponseData<CustomerCartData> responseData = new ResponseData<>();
        Optional<Customer> customer = customerService.getCustomerByID(customerID);
        Optional<CustomerCart> customerCart = customerCartService.getCutomerCartByID(customerID);

        if (!customer.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Customer not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        if (!customerCart.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Failed to get customer cart");

            return ResponseEntity.badRequest().body(responseData);
        }

        List<CustomerCartItem> customerCartItems = customerCartItemService
                .getCustomerCartItemByCustomerCart(customerCart.get());

        List<CustomerCartItemData> customerCartItemData = new ArrayList<>();

        for (CustomerCartItem item : customerCartItems) {
            customerCartItemData.add(new CustomerCartItemData(
                    item.getCustomerCartItemID(),
                    item.getMenu().getMenuID(),
                    item.getMenu().getMenuName(),
                    item.getMenu().getPrice(),
                    item.getItemQuantity()));
        }

        responseData.setStatus(true);
        responseData.setPayload(new CustomerCartData(customerID, customerCartItemData));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}/cart/item/{menuID}")
    public ResponseEntity<ResponseData<CustomerCartItemData>> getCustomerCartItemByMenu(
            @PathVariable("id") int customerID,
            @PathVariable("menuID") int menuID) {
        ResponseData<CustomerCartItemData> responseData = new ResponseData<>();
        Optional<CustomerCart> customerCart = customerCartService.getCutomerCartByID(customerID);
        Optional<Menu> menu = menuService.getMenuByID(menuID);

        Optional<CustomerCartItem> customerCartItem = customerCartItemService
                .getCustomerCartItemByCustomerCartAndMenu(customerCart.get(), menu.get());

        if (!customerCartItem.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Cart Item Not Found");
            return ResponseEntity.badRequest().body(responseData);
        }

        CustomerCartItemData customerCartItemData = new CustomerCartItemData(
                customerCartItem.get().getCustomerCartItemID(),
                customerCartItem.get().getMenu().getMenuID(),
                customerCartItem.get().getMenu().getMenuName(),
                customerCartItem.get().getMenu().getPrice(),
                customerCartItem.get().getItemQuantity());

        responseData.setStatus(true);
        responseData.setPayload(customerCartItemData);
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}/cart/item/{itemID}/delete")
    public ResponseEntity<ResponseData<CustomerCartItemData>> deleteCustomerCartItemByID(
            @PathVariable("id") int customerID,
            @PathVariable("itemID") int itemID) {
        ResponseData<CustomerCartItemData> responseData = new ResponseData<>();
        Optional<CustomerCart> customerCart = customerCartService.getCutomerCartByID(customerID);

        if (!customerCart.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Failed to delete item");
        }

        customerCartItemService.deleteCustomerCartItemByID(itemID);

        responseData.setStatus(true);
        responseData.getMessages().add("Item deleted successfully");
        return ResponseEntity.ok(responseData);
    }
}
