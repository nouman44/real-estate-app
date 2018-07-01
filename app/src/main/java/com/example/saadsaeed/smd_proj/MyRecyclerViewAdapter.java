package com.example.saadsaeed.smd_proj;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;



public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>
{
    private List<AccountClass> items;
    private int itemLayout;
    public MyRecyclerViewAdapter(List<AccountClass> items, int itemLayout)
    {

        this.items = items;
        this.itemLayout = itemLayout;
    }
    @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new MyViewHolder(v);
    }
    @Override public void onBindViewHolder(MyViewHolder holder, int position)
    {
        if(items != null && holder != null)
        {
            holder.name.setText(items.get(position).getName());
            holder.details.setText(items.get(position).getIcon());
            holder.Picture.setImageResource(items.get(position).getPicture());

        }
    }
    @Override public int getItemCount()
    {
        if(items != null)
            return items.size();
        else
            return 0;
    }
    public void updatevalues(AccountClass item)
    {

        //  items.add(item);
        notifyDataSetChanged();

    }
}
