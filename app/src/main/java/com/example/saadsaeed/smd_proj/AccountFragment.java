package com.example.saadsaeed.smd_proj;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class AccountFragment extends Fragment   implements RecyclerView.OnItemTouchListener  {

    private OnFragmentInteractionListener mListener;
    GestureDetector gestureDetector;
    public AccountFragment()
    {

        // Required empty public constructor
    }

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
         fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.activity_account, container, false);

        ConstraintLayout complexButtonLayout= (ConstraintLayout) rootView.findViewById(R.id.constraintLayout);


        complexButtonLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), AccountSettings.class);
                startActivity(intent);
            }
        });

          final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycleview);


         AccountClass c2= new AccountClass(R.drawable.upload,"Let you Post an Ad","Post An Add");
        AccountClass c3= new AccountClass(R.drawable.bookmark,"Show All of Your Saved Ads","Saved Ads");
        AccountClass c4= new AccountClass(R.drawable.settings,"Let you change Basic Settings","Settings");
        AccountClass c5= new AccountClass(R.drawable.about,"Status , Legal Information ","About");

        ArrayList<AccountClass> data= new ArrayList<AccountClass>();
 //       data.add(c1);
        data.add(c2);
        data.add(c3);
        data.add(c4);
        data.add(c5);

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(data, R.layout.layout2);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e)
            {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null)
                {
                    // checker(rv1.getChildAdapterPosition(child));
                    Integer x=rv.getChildAdapterPosition(child);
                     if(x.equals(0))
                    {

                        Intent i = new Intent(getActivity(),PostActivity.class);
                        startActivity(i);

                    }
                    if(x.equals(1))
                    {

                        Intent i = new Intent(getActivity(),SavedAds.class);
                        startActivity(i);
                    }
                    if(x.equals(2))
                    {
                         Intent i = new Intent(getActivity(),Settings.class);
                        startActivity(i);

                    }
                    if(x.equals(3))
                    {
                         Intent i = new Intent(getActivity(),AboutActivity.class);
                        startActivity(i);

                    }
                }
                return true;



            }
        }

        );

        rv.addOnItemTouchListener(this);
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

    public void checker(int k )
    {

    }

}
