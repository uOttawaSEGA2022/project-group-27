package com.example.mealerapp.Domain;

public class CuisineDomain {

    private String title;
    private String icon;


    public CuisineDomain(String title, String icon){
        this.title=title;
        this.icon=icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}