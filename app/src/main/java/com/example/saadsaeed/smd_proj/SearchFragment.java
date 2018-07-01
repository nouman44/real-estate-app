package com.example.saadsaeed.smd_proj;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.analytics.Tracker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class SearchFragment extends Fragment  implements View.OnClickListener,SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
    Context c;
    Button b;
    ArrayList <Ad> searchad = new ArrayList<Ad>() ;
    DatabaseReference mDatabase;
    private OnFragmentInteractionListener mListener;
    TextToSpeech tts;
    ListView list;
    ListViewAdapter adapter;
    android.widget.SearchView editsearch;

    String[] carNameList;
    ArrayList<Locationname> arraylist = new ArrayList<Locationname>();


    public SearchFragment() {
        // Required empty public constructor
    }

    public void setC(Context c) {
        this.c = c;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        Helpers.getall(getActivity());





        carNameList = new String[]{"lahore","Civic","cultux","mehran","swift","accord","rx-8","A3 Sedan","R8","alto","mehran","swift","accord","rx-8","A3 Sedan"};

        // Locate the ListView in listview_main.xml
        list = (ListView)view.findViewById(R.id.listview);




        for (int i = 0; i < carNameList.length; i++) {
            Locationname Locationname = new Locationname(carNameList[i]);
            // Binds all strings into an array
            arraylist.add(Locationname);
        }
        list.setVisibility(View.INVISIBLE);
        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(getActivity(), arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch =  view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener((android.widget.SearchView.OnQueryTextListener) this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

             //   Toast.makeText(getActivity(), ListViewAdapter.LocationnameList.get(position).getLocationname(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getActivity(),ShowSearched.class);
                i.putExtra("code",ListViewAdapter.LocationnameList.get(position).getLocationname());
                startActivity(i);

            }

        });


        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public boolean onQueryTextSubmit(String query) {


        Intent i = new Intent(getActivity(),ShowSearched.class);
        i.putExtra("code",query);

        startActivity(i);


        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        list.setVisibility(View.VISIBLE);
        String text = newText;

        if(text.length()==0)
        {
            list.setVisibility(View.INVISIBLE);
        }
        adapter.filter(text);
        return false;
    }

    @Override
    public void onClick(View view) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       // Helpers.allads.clear();

    }
}

