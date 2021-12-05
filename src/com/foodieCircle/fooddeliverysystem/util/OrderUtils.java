package com.foodieCircle.fooddeliverysystem.util;

import com.foodieCircle.fooddeliverysystem.model.Order;
import com.foodieCircle.fooddeliverysystem.model.OrderStatus;

public class OrderUtils {
    public static final Float THRESHOLD_DELIVERY_TIME = 150F;
    
    public static void deliveryTimeCalculation(Order order){
        float distance = order.getDistance();
        order.settravelTime(distance*Order.ORDER_DELIVERY_TIME_FOR_ONE_KM);
    }
    
    public static void getTotalTimeRequired(Order order){
        Float deliveryTime = order.getCookingTime() + order.getTravelTime() + order.getWaitingTime();
        if(deliveryTime<=THRESHOLD_DELIVERY_TIME){
            order.setdeliveryTime(order.getCookingTime() + order.getTravelTime() + order.getWaitingTime());
            order.setOrderStatus(OrderStatus.DELIVERED);
            order.setIsDeliverable(true);
        }else{
            order.setOrderStatus(OrderStatus.CANCELLED);
            order.setIsDeliverable(false);
        }
    }
    
}
