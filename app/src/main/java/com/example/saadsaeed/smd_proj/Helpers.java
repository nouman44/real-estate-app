package com.example.saadsaeed.smd_proj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.util.Base64;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by Nouman on 24-Apr-18.
 */

public class Helpers {

    public static ArrayList<AdView> allads= new ArrayList<>();
    public static ArrayList<AdView> allsavedads= new ArrayList<>();
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);


        return cm.getActiveNetworkInfo() != null;
    }

    public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    public static void getAds(Context c)

    {
        final Context context = c;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        //list = new ArrayList<>();
        mDatabase.child("users").child(FirebaseAuth.getInstance().getUid()).child("ads").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {

                Ad ad = dataSnapshot.getValue(Ad.class);
                SaveAdsAsync save = new SaveAdsAsync(context, ad);
                save.execute();
                //list.add(ad);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

    }
    public static void getall(Context c)
    {
        final Context context = c;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        //list = new ArrayList<>();
        mDatabase.child("adsto").addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {

                Ad ad = dataSnapshot.getValue(Ad.class);
                SaveAdsAsync save = new SaveAdsAsync(context, ad);
                save.execute();

                Bitmap image = null;
                try {
                    image = decodeFromFirebaseBase64(ad.picture);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                allads.add(new AdView(ad.title,ad.price,ad.description,image,ad.user,ad.date,ad.latitude,ad.longitude,ad.mapName,ad.name,ad.phoneNo,ad.location));


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

    }
    public static void getsavedall(Context c)
    {
        final Context context = c;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        //list = new ArrayList<>();
        mDatabase.child("adsto").addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {

                Ad ad = dataSnapshot.getValue(Ad.class);
                SaveAdsAsync save = new SaveAdsAsync(context, ad);
                save.execute();

                Bitmap image = null;
                try {
                    image = decodeFromFirebaseBase64(ad.picture);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                allsavedads.add(new AdView(ad.title,ad.price,ad.description,image,ad.user,ad.date,ad.latitude,ad.longitude,ad.mapName,ad.name,ad.phoneNo,ad.location));


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException
    {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
}
