package com.example.mealerapp.Objects;

import java.security.PrivateKey;
import java.util.UUID;

public class Purchase {
    //this is the meals which offered by clients
    private Meal meal;
    private String cook_ID;
    private String status;
    private String client_ID;
    private String ID;

    public Purchase(){}

    public Purchase(Meal meal, String cook_ID, String client_ID){
        this.meal = meal;
        this.cook_ID = cook_ID;
        this.client_ID = client_ID;
        this.status = "Pending";
        this.ID = UUID.randomUUID().toString();
    }

    public Purchase(Meal meal, String cook_ID, String client_ID, String ID){
        this.meal = meal;
        this.client_ID = client_ID;
        this.cook_ID = cook_ID;
        this.status = "Pending";
        this.ID = ID;
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

    public String getClient_ID() {
        return client_ID;
    }

    public String getID() {
        return ID;
    }


}
