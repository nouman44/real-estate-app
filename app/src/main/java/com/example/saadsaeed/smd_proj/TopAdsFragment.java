package com.example.saadsaeed.smd_proj;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class TopAdsFragment extends Fragment implements RecyclerView.OnItemTouchListener{

    DatabaseReference mDatabase;
    ArrayList<AdView> items= new ArrayList<>();
    ArrayList<AdView> items1= new ArrayList<>();
    AdAdapter mAdapter ;
    ArrayList<AdView> v= new ArrayList<AdView>();
    GestureDetector gestureDetector;

    private OnFragmentInteractionListener mListener;

    public TopAdsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         final View rootView = inflater.inflate(R.layout.fragment_top_ads, container, false);
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.ad_list1);
        mDatabase = FirebaseDatabase.getInstance().getReference();
       Helpers.getsavedall(getContext());
        v=Helpers.allsavedads;
int count=0;
        Toast.makeText(getActivity(), Integer.toString(v.size()), Toast.LENGTH_SHORT).show();
                for (int i=0;i<v.size();i++)
                {
                    count=0;
                    for (int j=i;j<v.size();j++)
                    {
                       if(v.get(i).title.equals(v.get(j).title))
                       {
                           count=count+1;
                       }


                    }
                    if(count>0)
                    {
                        items.add(v.get(i));
                    }

                }
        gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    // checker(rv1.getChildAdapterPosition(child));
                    Integer x = recyclerView.getChildAdapterPosition(child);

                    Intent i = new Intent(getActivity(), AdDescription.class);
                    i.putExtra("title", items.get(x).title);
                    i.putExtra("latitude", items.get(x).latitude);
                    i.putExtra("longitude", items.get(x).longitude);
                    i.putExtra("mapName", items.get(x).mapName);
                    i.putExtra("price", items.get(x).price);
                    i.putExtra("pic", items.get(x).picture);
                    i.putExtra("date", items.get(x).date);
                    i.putExtra("description", items.get(x).description);
                    i.putExtra("name", items.get(x).name);
                    i.putExtra("phoneNo", items.get(x).phoneNo);
                    i.putExtra("location", items.get(x).location);

                    startActivity(i);



                }
                return true;


            }
        }

        );
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        mAdapter = new AdAdapter(items,R.layout.ad_list_item);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(this);

         // Inflate the layout for this fragment
        return rootView;



    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException
    {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return true;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        gestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
