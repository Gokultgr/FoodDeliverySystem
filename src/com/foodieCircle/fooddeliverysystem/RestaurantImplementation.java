package com.foodieCircle.fooddeliverysystem;

import com.foodieCircle.fooddeliverysystem.model.Dishes;
import com.foodieCircle.fooddeliverysystem.model.Order;
import com.foodieCircle.fooddeliverysystem.model.Restaurant;
import com.foodieCircle.fooddeliverysystem.util.RestaurantUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class RestaurantImplementation {
    
    private static RestaurantImplementation restaurantImplementation = null;
    
    private RestaurantImplementation(){
    
    }
    
    public static RestaurantImplementation getInstance(){
        if(restaurantImplementation == null)
            restaurantImplementation = new RestaurantImplementation();
        return restaurantImplementation;
    }
    
    public void calculateFoodDeliveryTime(Restaurant restaurant){
        RestaurantUtils.processOrderList(restaurant);
    }
    
    public Restaurant createRestaurant(int cookingSlots, Queue<Order> orders){
        Restaurant restaurant = new Restaurant(cookingSlots);
        Map<String, Dishes> menu = new HashMap<>();
        menu.put("M", new Dishes("M",29,2));
        menu.put("A", new Dishes("A",17,1));
        restaurant.setMenu(menu);
        restaurant.setOrderQueue(orders);
        return restaurant;
    }
}
