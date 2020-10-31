package com.example.projectiteration1.model;

import java.util.ArrayList;

public class RestaurantsList {
    private ArrayList<Restaurant> restaurants = new ArrayList<>();

    /*
        Singleton support
    */
    private RestaurantsList() {
        // To prevent from instantiating
    }
    private static RestaurantsList instance;
    public static RestaurantsList getInstance() {
        if (instance == null) {
            instance = new RestaurantsList();
        }
        return instance;
    }

    public void add(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

}
