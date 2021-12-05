package com.foodieCircle.fooddeliverysystem.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Restaurant {
    int cookingSlots;
    Map<String,Dishes> menu;
    int available_cooking_slots;
    Queue<Order> order_queue;
    PriorityQueue<Order> inProgressSet;
    List<Order> completedOrder;
    float nextWaitingTime;
    
    public Restaurant(int cookingSlots){
        this.cookingSlots = cookingSlots;
        order_queue = new LinkedList<>();
        this.available_cooking_slots = cookingSlots;
        menu = new HashMap<>();
        inProgressSet = new PriorityQueue<>((Order order1, Order order2) -> order1.getdeliveryTime().compareTo(order2.getdeliveryTime()));
        completedOrder = new ArrayList<>();
        nextWaitingTime = 0;
    }
    
    public int getCookingSlots(){
        return cookingSlots;
    }
    
    public Queue getOrderQueue(){
        return order_queue;
    }
    
    public void setOrderQueue(Queue<Order> order_queue){
        this.order_queue = order_queue;
    }
    
    public int getAvailableCookingSlots(){
        return available_cooking_slots;
    }
    
    public void setAvailableCookingSlots(int available_cooking_slots){
        this.available_cooking_slots = available_cooking_slots;
    }
    
    public Map<String,Dishes> getMenu(){
        return menu;
    }
    
    public void setMenu(Map<String,Dishes> menu){
        this.menu = menu;
    }

    public PriorityQueue<Order> getInProgressMap(){
        return inProgressSet;
    }
    
    public List<Order> getCompletedOrder() {
        return completedOrder;
    }
    
    public Float getDelayTime(){
        return nextWaitingTime;
    }
    
    public void setDelayTime(float nextWaitingTime){
        this.nextWaitingTime = nextWaitingTime;
    }
}
