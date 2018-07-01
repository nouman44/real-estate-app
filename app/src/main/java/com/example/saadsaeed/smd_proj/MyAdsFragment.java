package com.example.saadsaeed.smd_proj;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class MyAdsFragment extends Fragment implements RecyclerView.OnItemTouchListener {

    DatabaseReference mDatabase;
    ArrayList<AdView> items = new ArrayList<>();
    AdAdapter mAdapter;
     TextToSpeech tts;
    GestureDetector gestureDetector;
    private OnFragmentInteractionListener mListener;

    public MyAdsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /*
    public void onclick1(View V)
    {
        tts=new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.US);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{
                        tts.speak("Place an ad", TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });


    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_my_ads, container, false);
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.ad_list);

















        mDatabase = FirebaseDatabase.getInstance().getReference();
        DownloadAdsAsync download = new DownloadAdsAsync(items,getActivity());

        if(Helpers.isInternetAvailable() || Helpers.isNetworkConnected(getActivity())) {

            mDatabase.child("users").child(FirebaseAuth.getInstance().getUid()).child("ads").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {

                    Ad ad = dataSnapshot.getValue(Ad.class);

                    Bitmap image = null;
                    try {
                        image = decodeFromFirebaseBase64(ad.picture);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //items.add(new AdView(ad.title, "Location= " + ad.location, image, "Price= " + ad.value, "Date= " + ad.date));

                    AdView adView = new AdView(ad.title,ad.price,ad.description,image,ad.user,ad.date,ad.latitude,ad.longitude,ad.mapName,ad.name,ad.phoneNo,ad.location);

                    ArrayList<AdView> copy = new ArrayList<>();

                    for (AdView a : items) {
                        copy.add(a);
                    }

                    boolean check = true;
                    if (copy.size() > 0) {
                        for (AdView a : copy) {
                            if (a.title == adView.title && a.description == adView.description && a.date == adView.date && a.price == adView.price)
                                check = false;
                        }

                        if (check) {
                            items.add(adView);
                            mAdapter.notifyDataSetChanged();
                        }

                    } else {
                        items.add(adView);
                        mAdapter.notifyDataSetChanged();
                    }


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
        else{
            download.execute();
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


        mAdapter = new AdAdapter(items, R.layout.ad_list_item);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(this);

        Button btn = (Button) rootView.findViewById(R.id.button6);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts=new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {
                        // TODO Auto-generated method stub
                        if(status == TextToSpeech.SUCCESS){
                            int result=tts.setLanguage(Locale.US);
                            if(result==TextToSpeech.LANG_MISSING_DATA ||
                                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                                Log.e("error", "This Language is not supported");
                            }
                            else{
                                tts.speak("Place an ad", TextToSpeech.QUEUE_FLUSH, null);
                            }
                        }
                        else
                            Log.e("error", "Initilization Failed!");
                    }
                });

                Intent i = new Intent(getActivity(), PostActivity.class);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment

        //  mAdapter.notifyDataSetChanged();
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
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
