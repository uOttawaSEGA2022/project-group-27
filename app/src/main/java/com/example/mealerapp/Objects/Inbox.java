package com.example.mealerapp.Objects;

import java.util.ArrayList;
import java.util.List;

public class Inbox {

    private List<Notification> notifs;

    public Inbox(Notification notif) {
        notifs = new ArrayList<>();
        notifs.add(notif);

    }

    public Inbox(List<Notification> notifs){
        this.notifs = notifs;
    }

}
