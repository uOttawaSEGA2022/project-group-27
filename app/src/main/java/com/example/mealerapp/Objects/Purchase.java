package com.example.mealerapp.Objects;

import java.security.PrivateKey;

public class Purchase {
    //this is the meals which offered by clients
    private Meal meal;
    private String cook_ID;
    private String status;

    public Purchase(){}

    public Purchase(Meal meal, String cook_ID){
        this.meal = meal;
        this.cook_ID = cook_ID;
        this.status = "Pending";
    }

    public Meal getMeal() {
        return meal;
    }

    public String getStatus() {
        return status;
    }

    public String getCook_ID() {
        return cook_ID;
    }
}
