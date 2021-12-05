/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodieCircle.fooddeliverysystem.util;

import com.foodieCircle.fooddeliverysystem.model.Dishes;
import com.foodieCircle.fooddeliverysystem.model.Order;
import com.foodieCircle.fooddeliverysystem.model.OrderStatus;
import com.foodieCircle.fooddeliverysystem.model.Restaurant;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class RestaurantUtils {
    /*To check whether restaurant can accomadate Order*/
    public static boolean checkCookingSlots(Restaurant restaurant, Order order){
        int cookingSlots = getCookingSlotsRequired(restaurant,order);
        return restaurant.getCookingSlots() > cookingSlots;
    }
    
    /*To get Cooking Slots required for the Order*/
    public static int getCookingSlotsRequired(Restaurant restaurant, Order order){
        Map<String,Integer> meals = order.getMealsList();
        int cookingSlotsReq = 0;
        for(String meal:meals.keySet()){
            int quantity = meals.get(meal);
            Dishes dish = restaurant.getMenu().get(meal);
            cookingSlotsReq += dish.getcookingSlots()*quantity;
        }
        return cookingSlotsReq;
    }
    
    /*To get Time required to prepare Order*/
    public static Float getCookingTimeRequired(Restaurant restaurant, Order order){
        Map<String,Integer> meals = order.getMealsList();
        Float cookingTimeReq = 0F;
        for(String meal:meals.keySet()){
            
            Dishes dish = restaurant.getMenu().get(meal);
            cookingTimeReq = Math.max(cookingTimeReq,dish.getCookingTime());
        }
        order.setCookingTime(cookingTimeReq);
        return cookingTimeReq;
    }
    
    /*To move order to cooking phase*/
    public static boolean takeOrder(Restaurant restaurant, Order order){
        int cookingSlots = getCookingSlotsRequired(restaurant,order);
        int availableSlots = restaurant.getAvailableCookingSlots();
        if(cookingSlots<=availableSlots){
            Float cookingTime = getCookingTimeRequired(restaurant,order);
            order.setCookingTime(cookingTime);
            availableSlots -= cookingSlots;
            order.setwaitingTime(restaurant.getDelayTime());
            OrderUtils.deliveryTimeCalculation(order);
            order.setOrderStatus(OrderStatus.IN_PROGRESS);
        }else{
            return false;
        }
        restaurant.setAvailableCookingSlots(availableSlots);
        return true;
    }
    
    /*To remove order from Cooking phase*/
    public static void deliverOrder(Restaurant restaurant, Order order){
        int cookingSlots = getCookingSlotsRequired(restaurant,order);
        int availableSlots = restaurant.getAvailableCookingSlots();
        order.setOrderStatus(OrderStatus.DELIVERED);
        restaurant.setAvailableCookingSlots(availableSlots+cookingSlots);
    }
    
    public static void completeAllOrdersPending(Restaurant restaurant)
    {
        PriorityQueue<Order> inProgressQueu = restaurant.getInProgressMap();
        for(Order order:inProgressQueu){
            deliverOrder(restaurant,order);
        }
    }
    /*Process order and provide delivery Time*/
    public static void processOrderList(Restaurant restaurant)
    {
        Queue orders = restaurant.getOrderQueue();
        List<Order> completedOrder = restaurant.getCompletedOrder();
        while(!orders.isEmpty()){
            Order order = (Order) orders.peek();
            if(checkCookingSlots(restaurant,order)){
                PriorityQueue<Order> inProgressQueu = restaurant.getInProgressMap();
                
                if(takeOrder(restaurant,order)){
                    OrderUtils.deliveryTimeCalculation(order);
                    OrderUtils.getTotalTimeRequired(order);
                    
                    if(order.getIsDeliverable()){
                        orders.poll();
                        inProgressQueu.add(order);
                        order.setwaitingTime(restaurant.getDelayTime());
                        System.out.println("Order "+ order.getOrderId() +" will get delivered in " + order.getdeliveryTime() + " minutes");
                    }else{
                        orders.poll();
                        System.out.println("Order "+order.getOrderId()+" is denied because the waiting period is more than threshold");
                    }
                    
                }else{
                    Order completedorder = inProgressQueu.poll();
                    restaurant.setDelayTime(completedorder.getdeliveryTime());
                    deliverOrder(restaurant,completedorder);
                    order.setOrderStatus(OrderStatus.WAITING);
                    restaurant.setAvailableCookingSlots(restaurant.getAvailableCookingSlots());
                    completedOrder.add(completedorder);
                }
                
            }else{
                orders.poll();
                System.out.println("Order "+order.getOrderId()+" is denied because the restaurant cannot accommodate it.");
            }
        }
        completeAllOrdersPending(restaurant);
    }
}
