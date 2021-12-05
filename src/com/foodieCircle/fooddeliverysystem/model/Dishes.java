package com.foodieCircle.fooddeliverysystem.model;

public class Dishes {
    private final String dish_name;
    private final float cooking_time;
    private final int cooking_slots;
    
    public Dishes(String dish_name, float cooking_time, int cooking_slots){
        this.dish_name = dish_name;
        this.cooking_slots = cooking_slots;
        this.cooking_time = cooking_time;
    }
    
    public String getDishName(){
        return dish_name;
    }
    
    public float getCookingTime(){
        return cooking_time;
    }
    
    public int getcookingSlots(){
        return cooking_slots;
    }
}
