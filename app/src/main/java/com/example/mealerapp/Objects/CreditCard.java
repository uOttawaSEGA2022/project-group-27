package com.example.mealerapp.Objects;


import java.util.Date;

public class CreditCard {

    private String nameOnCard;
    private String expirationDate;
    private long cardNumber;
    private int CVV;

    public CreditCard(){}

    public CreditCard(String nameOnCard, long cardNumber, String expirationDate, int CVV){
        this.nameOnCard = nameOnCard;
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
        this.CVV = CVV;

    }




    public String getNameOnCard() {
        return this.nameOnCard;
    }

    public String getExpirationDate(){
        return this.expirationDate;
    }

    public long getCardNumber(){
        return this.cardNumber;
    }

    public int getCVV(){
        return this.CVV;
    }

}
