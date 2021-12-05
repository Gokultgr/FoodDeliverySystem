package com.foodieCircle.fooddeliverysystem.model;

public enum OrderStatus {
    WAITING(1),
    IN_PROGRESS(2),
    DELIVERED(3),
    CANCELLED(4);
    
    private int value;
    private OrderStatus(int value){
        this.value = value;
    }
}
