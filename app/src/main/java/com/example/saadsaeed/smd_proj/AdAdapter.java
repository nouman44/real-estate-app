package com.example.saadsaeed.smd_proj;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

/**
 * Created by Nouman on 21-Apr-18.
 */

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.AdViewHolder> {
    private List<AdView> items;
    private int itemLayout;
    private Context context;

    public AdAdapter(List<AdView> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public AdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new AdViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdViewHolder holder, int position) {
        if (items != null && holder != null) {
            holder.setValues(items.get(position));
        }

    }

    @Override
    public int getItemCount() {
        if(items != null)
            return items.size();
        else
            return 0;
    }

    public class AdViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView Image;
        public TextView Price;
        public TextView date;

        public AdViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textVie22);
            Image = (ImageView) view.findViewById(R.id.imageView22);
            Price = (TextView) view.findViewById(R.id.textView23);
            date = (TextView) view.findViewById(R.id.editText13);


        }

        public void setValues(AdView ad)
        {
            title.setText(ad.getTitle());
            Image.setImageBitmap(ad.getPicture());
            Price.setText(ad.getPrice());
            date.setText(ad.getDate());

        }

    }
}
