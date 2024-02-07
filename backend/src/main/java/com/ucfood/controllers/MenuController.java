package com.ucfood.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ucfood.dto.ResponseData;
import com.ucfood.dto.menu.MenuData;
import com.ucfood.dto.menu.MenuListData;
import com.ucfood.models.entities.Menu;
import com.ucfood.models.entities.Restaurant;
import com.ucfood.services.MenuService;
import com.ucfood.services.RestaurantService;
import com.ucfood.utils.ImageConverter;

@RestController
@RequestMapping("/api/restaurant")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/{id}/menu")
    public ResponseEntity<ResponseData<MenuData>> createMenu(
            @PathVariable("id") int restaurantID,
            @RequestParam String menuName,
            @RequestParam int price,
            @RequestParam int stock,
            @RequestParam(required = false) MultipartFile menuImage) {
        ResponseData<MenuData> responseData = new ResponseData<>();
        Optional<Restaurant> restaurant = restaurantService.getRestaurantByID(restaurantID);

        if (!restaurant.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Restaurant not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        byte[] menuImageByte = null;

        if (menuImage != null) {
            try {
                menuImageByte = ImageConverter.compressImage(menuImage.getBytes());
            } catch (IOException e) {
                responseData.setStatus(false);
                responseData.getMessages().add("Failed upload menu image");

                return ResponseEntity.badRequest().body(responseData);
            }
        }

        Menu menu = menuService.createMenu(
                restaurant.get(),
                menuName,
                price,
                stock,
                menuImageByte);

        MenuData menuData = new MenuData(
                menu.getMenuID(),
                menu.getMenuName(),
                menu.getPrice(),
                menu.getStock());

        responseData.setStatus(true);
        responseData.getMessages().add("Create menu success");
        responseData.setPayload(menuData);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}/menu/{menuID}")
    public ResponseEntity<ResponseData<MenuListData>> getMenuByID(
            @PathVariable("id") int restaurantID,
            @PathVariable("menuID") int menuID) {
        ResponseData<MenuListData> responseData = new ResponseData<>();
        List<MenuData> listMenuData = new ArrayList<>();

        Optional<Restaurant> restaurant = restaurantService.getRestaurantByID(restaurantID);

        if (!restaurant.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Restaurant not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        Optional<Menu> menu = menuService.getMenuByID(menuID);

        if (!menu.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Menu not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        MenuData menuData = new MenuData(
                menu.get().getMenuID(),
                menu.get().getMenuName(),
                menu.get().getPrice(),
                menu.get().getStock());

        listMenuData.add(menuData);

        responseData.setStatus(true);
        responseData.setPayload(new MenuListData(listMenuData));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}/menu/all")
    public ResponseEntity<ResponseData<MenuListData>> getAllMenuByRestaurantID(
            @PathVariable("id") int restaurantID) {
        ResponseData<MenuListData> responseData = new ResponseData<>();
        List<MenuData> menuData = new ArrayList<>();

        Optional<Restaurant> restaurant = restaurantService.getRestaurantByID(restaurantID);

        if (!restaurant.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Restaurant not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        List<Menu> listMenu = menuService.getAllMenuByRestaurant(restaurant.get());

        for (Menu menu : listMenu) {
            menuData.add(new MenuData(
                    menu.getMenuID(),
                    menu.getMenuName(),
                    menu.getPrice(),
                    menu.getStock()));
        }

        responseData.setStatus(true);
        responseData.setPayload(new MenuListData(menuData));

        return ResponseEntity.ok(responseData);
    }

    @PutMapping("{id}/menu/{menuID}")
    public ResponseEntity<ResponseData<MenuListData>> updateMenu(
            @PathVariable("id") int restaurantID,
            @PathVariable("menuID") int menuID,
            @RequestParam String menuName,
            @RequestParam int price,
            @RequestParam int stock,
            @RequestParam MultipartFile menuImage) {

        ResponseData<MenuListData> responseData = new ResponseData<>();
        Optional<Restaurant> restaurant = restaurantService.getRestaurantByID(restaurantID);
        List<MenuData> listMenuData = new ArrayList<>();

        if (restaurant.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Restaurant not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        Optional<Menu> menu = menuService.getMenuByID(menuID);

        if (!menu.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Menu not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        byte[] menuImageByte = null;

        if (menuImage != null) {
            try {
                menuImageByte = ImageConverter.compressImage(menuImage.getBytes());
            } catch (IOException e) {
                responseData.setStatus(false);
                responseData.getMessages().add("Failed upload menu image");

                return ResponseEntity.badRequest().body(responseData);
            }
        } else {
            menuImageByte = menuService.getMenuImageByID(menuID);
        }

        menuService.updateMenu(
                menuID,
                restaurant.get(),
                menuName,
                price,
                stock,
                menuImageByte);

        MenuData menuData = new MenuData(
                menuID,
                menuName,
                price,
                stock);

        listMenuData.add(menuData);

        responseData.setStatus(true);
        responseData.setPayload(new MenuListData(listMenuData));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}/menu/{menuID}")
    public ResponseEntity<ResponseData<Menu>> deleteMenuByID(
            @PathVariable("id") int restaurantID,
            @PathVariable("menuID") int menuID) {
        ResponseData<Menu> responseData = new ResponseData<>();
        Optional<Restaurant> restaurant = restaurantService.getRestaurantByID(restaurantID);
        Optional<Menu> menu = menuService.getMenuByID(menuID);

        if (!restaurant.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Restaurant not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        if (!menu.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Menu not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        menuService.deleteMenuByID(menuID);

        responseData.setStatus(true);
        responseData.getMessages().add(menu.get().getMenuName() + " deleted");

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("{id}/menu/{menuID}/image")
    public ResponseEntity<?> getMenuImageByID(@PathVariable("id") int restaurantID,
            @PathVariable("menuID") int menuID) {
        ResponseData<Menu> responseData = new ResponseData<>();
        Optional<Restaurant> restaurant = restaurantService.getRestaurantByID(restaurantID);

        if (!restaurant.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Restaurant not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        Optional<Menu> menu = menuService.getMenuByID(menuID);

        if (!menu.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Menu not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        byte[] picture = menuService.getMenuImageByID(menuID);

        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(picture);
    }
}
