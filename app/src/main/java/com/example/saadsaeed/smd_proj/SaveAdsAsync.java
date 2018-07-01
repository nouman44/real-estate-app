package com.example.saadsaeed.smd_proj;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by FUTURE LAPTOP on 4/28/2018.
 */

public class SaveAdsAsync extends AsyncTask<Void, Void, Void> {


    Context context;

    MyDatabase db;
    Ad ad;

    public SaveAdsAsync(Context c, Ad ad) {
        context = c;

        db = MyDatabase.getAppDatabase(c);
        this.ad=ad;

    }


    @Override
    protected Void doInBackground(Void... v) {
        /*Ad ad[]=new Ad[Helpers.list.size()];
        for(int i=0;i<Helpers.list.size();i++)
        {
            ad[i]= Helpers.list.get(i);
        }*/
        db.AdDao().insertAll(ad);

        return null;
    }


}