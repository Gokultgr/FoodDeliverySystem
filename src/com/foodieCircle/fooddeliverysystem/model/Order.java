package com.foodieCircle.fooddeliverysystem.model;

import java.util.Map;

public class Order {

    private final int orderId;
    private final Map<String,Integer> meals;
    private final float distance;
    private boolean isdeliverable = false;
    private float deliveryTime = -1;
    private OrderStatus status;
    public static final int ORDER_DELIVERY_TIME_FOR_ONE_KM = 8;
    private float waitingTime;
    private float cookingTime;
    private float travelTime;
    
    public Order(int orderId, Map<String,Integer> meals, float distance){
        this.orderId = orderId;
        this.meals = meals;
        this.distance = distance;
    }
    
    public int getOrderId(){
        return orderId;
    }
    
    public Map<String,Integer> getMealsList(){
        return meals;
    }
    
    public float getDistance(){
        return distance;
    }
    
    public boolean getIsDeliverable(){
        return isdeliverable;
    }
    
    public void setIsDeliverable(boolean isdeliverable){
        this.isdeliverable = isdeliverable;
    }
    
    public Float getdeliveryTime(){
        return deliveryTime;
    }
    
    public void setdeliveryTime(float deliveryTime){
        this.deliveryTime = deliveryTime;
    }

    public void setCookingTime(float cookingTime) {
        this.cookingTime = cookingTime;
    }
    
    public void setwaitingTime(float waitingTime) {
        this.waitingTime = waitingTime;
    }
    
    public void settravelTime(float travelTime) {
        this.travelTime = travelTime;
    }
    
    public Float getCookingTime(){
        return cookingTime;
    }
    
    public float getTravelTime(){
        return travelTime;
    }
    
    public float getWaitingTime(){
        return waitingTime;
    }
    
    public OrderStatus getOrderStatus(){
        return status;
    }
    
    public void setOrderStatus(OrderStatus status){
        this.status = status;
    }
}
