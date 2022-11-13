package com.example.mealerapp.Objects;

public class Client extends User {


//    private List<CreditCard> CreditCards;

    private String Role;

    public Client(){}

    public Client (String Role, String firstName, String lastName, String Address, String Email, String Password, String UID){
        super(Role, firstName, lastName, Address, Email, Password, UID);
//        this.CreditCards.add(CC);
    }



/*
    public void addCreditCard(CreditCard CC){
        CreditCards.add(CC);
    }
*/
}
