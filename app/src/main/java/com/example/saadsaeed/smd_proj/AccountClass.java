package com.example.saadsaeed.smd_proj;


/**
 * Created by Saad Saeed on 4/21/2018.
 */

public class AccountClass {

    int picture ;
    String icon ;
    String name;

    public AccountClass(int picture, String icon, String name) {
        this.picture = picture;
        this.icon = icon;
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }
}
