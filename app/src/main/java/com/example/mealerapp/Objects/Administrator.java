package com.example.mealerapp.Objects;

import java.util.Date;

public class Administrator extends User {

    public Administrator () {}

    public Administrator(String Role, String firstName, String lastName, String Address, String Email, String Password, String UID){
        super(Role, firstName, lastName, Address, Email, Password, UID);

    }

    //Yo idk how you do the login but when you login if Role == cook, then check if suspended == true.
    //If it is, then if until is undefined then toast with like ur suspended
    //else if Calander.getTime <= c.until then change suspended = false and login.

    public Administrator(User user){
        super(user.getRole(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getEmail(), user.getPassword(), user.getUID());
    }

    public void tempSuspend(Cook c, Date d){

        c.setSuspended(true);
        c.setUntil(d);
    }

    public void permSuspend(Cook c){

        c.setSuspended(true);
    }
    /*
    Ill leave this for now because the complaint class isnt complete.
    I assume complaint class will have a like delete method which will just remove it from the DB(Which i also dont know how to do
     */
    public void dismissComplaint(Complaint c){

    }
}
