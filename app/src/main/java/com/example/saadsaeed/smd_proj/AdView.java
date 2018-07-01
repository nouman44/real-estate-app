package com.example.saadsaeed.smd_proj;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nouman on 21-Apr-18.
 */

public class AdView {

    String title;
    String price;
    String description;
    Bitmap picture;
    String user;
    String date;
    Double latitude;
    Double longitude;
    String mapName;
    String name;
    String phoneNo;
    String location;

    public AdView(String title, String price, String description, Bitmap picture, String user, String date, Double latitude, Double longitude, String mapName, String name, String phoneNo, String location) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.picture = picture;
        this.user = user;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mapName = mapName;
        this.name = name;
        this.phoneNo = phoneNo;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
}
