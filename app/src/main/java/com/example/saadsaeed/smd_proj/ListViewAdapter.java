package com.example.saadsaeed.smd_proj;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    static List<Locationname> LocationnameList =  new ArrayList<Locationname>();
    private ArrayList<Locationname> arraylist;

    public ListViewAdapter(Context context, List<Locationname> LocationnameList) {
        mContext = context;
        this.LocationnameList = LocationnameList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Locationname>();
        this.arraylist.addAll(LocationnameList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return LocationnameList.size();
    }

    @Override
    public Locationname getItem(int position) {
        return LocationnameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(LocationnameList.get(position).getLocationname());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        LocationnameList.clear();
        if (charText.length() == 0) {
            LocationnameList.addAll(arraylist);
        } else {
            for (Locationname wp : arraylist) {
                if (wp.getLocationname().toLowerCase(Locale.getDefault()).contains(charText)) {
                    LocationnameList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}