# FoodDeliverySystem
Food Delivery System

java -jar ~dist/FoodDeliverySystem.jar /input.json

Input(as a json file)

[

    {
    
        "orderId": 12,
        
        "meals": [
        
            "A","A"
            
        ],
        
        "distance": 5
        
    },
    
    {
    
        "orderId": 21,
        
        "meals": [
        
            "A","M"
            
        ],
        
        "distance": 1
        
    },
    
    {
    
        "orderId": 14,
        
        "meals": [
        
            "M","M","M","M","A","A","A"
            
        ],
        
        "distance": 10
        
    },
    
    {
    
        "orderId": 32,
        
        "meals": [
        
        "M"
        
        ],
        
        "distance": 0.1
        
    },
    
    {
    
        "orderId": 22,
        
        "meals": [
        
            "A"
            
        ],
        
        "distance": 3
        
    },
    
    {
    
        "orderId": 18,
        
        "meals": [
        
            "A","M","M","M"
            
        ],
        
        "distance": 7
        
    }
    
]

Output:::

Order 12 will get delivered in 57.0 minutes

Order 21 will get delivered in 37.0 minutes

Order 14 is denied because the restaurant cannot accommodate it.

Order 32 will get delivered in 29.8 minutes

Order 22 will get delivered in 70.8 minutes

Order 18 is denied because the restaurant cannot accommodate it.

