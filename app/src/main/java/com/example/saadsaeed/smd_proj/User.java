package com.example.saadsaeed.smd_proj;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User
{

    public String username;
    public String email;
    public String password;
    public String phoneno;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public User(String username, String email, String password, String phoneno) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneno = phoneno;
    }
}