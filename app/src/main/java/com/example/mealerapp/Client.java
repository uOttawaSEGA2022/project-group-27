package com.example.mealerapp;

import java.util.ArrayList;
import java.util.List;

public class Client extends User{


//    private List<CreditCard> CreditCards;

    private String Role;

    public Client(){}

    public Client (String Role, String firstName, String lastName, String Address, String Email, String Password){
        super(Role, firstName, lastName, Address, Email, Password);
//        this.CreditCards.add(CC);
    }



/*
    public void addCreditCard(CreditCard CC){
        CreditCards.add(CC);
    }
*/
}
