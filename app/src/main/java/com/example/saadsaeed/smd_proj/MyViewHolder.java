package com.example.saadsaeed.smd_proj;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder
{
    public TextView name;
    public ImageView Picture;
    public TextView details;

    public MyViewHolder(View view)
    {
        super(view);
        name = (TextView) view.findViewById(R.id.textView14);
        Picture = (ImageView) view.findViewById(R.id.imageView9);
        details = (TextView) view.findViewById(R.id.textView16);

    }

}
