package com.example.saadsaeed.smd_proj;

/**
 * Created by FUTURE LAPTOP on 4/28/2018.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.saadsaeed.smd_proj.Ad;
import com.example.saadsaeed.smd_proj.AdView;
import com.example.saadsaeed.smd_proj.Helpers;
import com.example.saadsaeed.smd_proj.MyDatabase;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by FUTURE LAPTOP on 4/28/2018.
 */

public class DownloadAdsAsync extends AsyncTask<Void, Void, Void> {


    Context context;
    ArrayList<AdView> ads;
    ArrayList<Ad> list;
    MyDatabase db;

    public DownloadAdsAsync(ArrayList<AdView> items, Context c) {

        context = c;
        ads = items;
        db = MyDatabase.getAppDatabase(c);
        list = new ArrayList<>();
    }


    @Override
    protected Void doInBackground(Void... v) {
        list = (ArrayList) db.AdDao().getAll();
        for (int i = 0; i < list.size(); i++) {
            Ad ad = list.get(i);
            Bitmap image = null;
            try {
                image = Helpers.decodeFromFirebaseBase64(ad.getPicture());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ads.add(new AdView(ad.title,ad.price,ad.description,image,ad.user,ad.date,ad.latitude, ad.longitude,ad.mapName,ad.name,ad.phoneNo,ad.location));

        }
        return null;
    }
}

