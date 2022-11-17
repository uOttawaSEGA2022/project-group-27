package com.example.mealerapp.Objects;

import java.util.Date;

public class Notification {

    private Date timeStamp;
    private String content;

    public Notification() {}

    public Notification(Date timeStamp, String content){
        this.timeStamp = timeStamp;
        this.content = content;
    }



}
