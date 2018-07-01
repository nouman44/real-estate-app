package com.example.saadsaeed.smd_proj;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Nouman on 21-Apr-18.
 */

@Entity(tableName = "ad")
public class Ad {
    @PrimaryKey(autoGenerate = true)
    private int adId;

    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "price")
    String price;
    @ColumnInfo(name = "description")
    String description;
    @ColumnInfo(name = "picture")
    String picture;
    @ColumnInfo(name = "user")
    String user;
    @ColumnInfo(name = "date")
    String date;
    @ColumnInfo(name = "latitude")
    Double latitude;
    @ColumnInfo(name = "longitude")
    Double longitude;
    @ColumnInfo(name = "mapName")
    String mapName;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "phoneNo")
    String phoneNo;
    @ColumnInfo(name = "location")
    String location;

    public Ad(){
        
    }

    public Ad(String title, String price, String description, String picture, String user, String date, Double latitude, Double longitude, String mapName, String name, String phoneNo, String location) {
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

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
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
