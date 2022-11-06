package com.example.mealerapp;

import java.util.Date;

public class Administrator extends User {

    public Administrator(String Role, String firstName, String lastName, String Address, String Email, String Password){
        super(Role, firstName, lastName, Address, Email, Password);

    }

    //Yo idk how you do the login but when you login if Role == cook, then check if suspended == true.
    //If it is, then if until is undefined then toast with like ur suspended
    //else if Calander.getTime <= c.until then change suspended = false and login.

    public void tempSus(Cook c, Date d){

        c.setSuspended(true);
        c.setUntil(d);
    }

    public void permSus(Cook c){

        c.suspended = true;
    }
    /*
    Ill leave this for now because the complaint class isnt complete.
    I assume complaint class will have a like delete method which will just remove it from the DB(Which i also dont know how to do

    public void dismissComplaint(Complaint c){

    }

     */
}
