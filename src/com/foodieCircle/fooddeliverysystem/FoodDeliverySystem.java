package com.foodieCircle.fooddeliverysystem;

import com.foodieCircle.fooddeliverysystem.model.Order;
import com.foodieCircle.fooddeliverysystem.model.Restaurant;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FoodDeliverySystem {

    private static Queue<Order> readInput(String fileName){
        Queue<Order> orderList = new LinkedList<>();
        JSONParser parser = new JSONParser();
        try{
            JSONArray jsonArr = (JSONArray) parser.parse(new FileReader(fileName));
            for(Object obj:jsonArr){
                JSONObject jsonObject = (JSONObject) obj;
                int orderId = Integer.parseInt(jsonObject.get("orderId")+"");
                JSONArray arr = (JSONArray) jsonObject.get("meals");
                Map<String,Integer> meals = new HashMap(); 
                for(Object o:arr){
                    String dish = o.toString();
                    if(meals.get(dish)==null){
                        meals.put(dish, 1);
                    }else{
                        int count = meals.get(dish);
                        meals.put(dish, ++count);
                    }
                }
                Float distance = Float.parseFloat(jsonObject.get("distance")+"");
                Order order = new Order(orderId,meals,distance);
                orderList.add(order);
            }
        }catch(ParseException ex){
            System.out.println("File format incorrect");
        }catch(FileNotFoundException ex){
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("File cannot be accessed");
        }
        return orderList;
    }
    
    public static void main(String[] args) {
        RestaurantImplementation restaurantImplementation = RestaurantImplementation.getInstance();
        Queue<Order> orderList = readInput(args[0]);
        Restaurant restaurant = restaurantImplementation.createRestaurant(7,orderList);
        restaurantImplementation.calculateFoodDeliveryTime(restaurant);
    }
    
}
