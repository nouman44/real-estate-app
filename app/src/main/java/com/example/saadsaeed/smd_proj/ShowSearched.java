package com.example.saadsaeed.smd_proj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
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
import java.util.Locale;

public class ShowSearched extends AppCompatActivity implements RecyclerView.OnItemTouchListener  {

    DatabaseReference mDatabase;
    ArrayList<AdView> items = new ArrayList<>();
    AdAdapter2 mAdapter;

    GestureDetector gestureDetector;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_searched);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rview);
        Intent i = getIntent();


        String code=getIntent().getExtras().getString("code");
        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
            for (int k =0;k<Helpers.allads.size();k++)
            {
                if(Helpers.allads.get(k).location.contains(code))
                {
                    //for
                    items.add(Helpers.allads.get(k));
                }
                
            }


        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    // checker(rv1.getChildAdapterPosition(child));
                    Integer x = recyclerView.getChildAdapterPosition(child);

                   // Toast.makeText(ShowSearched.this, items.get(x).latitude.toString(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ShowSearched.this, AdDescription1.class);
                    i.putExtra("title", items.get(x).title);
                    i.putExtra("latitude", items.get(x).latitude);
                    i.putExtra("longitude", items.get(x).longitude);
                    i.putExtra("mapName", items.get(x).mapName);
                    i.putExtra("price", items.get(x).price);
                    i.putExtra("pic", items.get(x).picture);
                    i.putExtra("date", items.get(x).date);
                    i.putExtra("description", items.get(x).description);
                    i.putExtra("name", items.get(x).phoneNo);
                    i.putExtra("pno", items.get(x).name);


                    startActivity(i);


                }
                return true;


            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new AdAdapter2(items, R.layout.searchedaddslayout);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(this);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        items.clear();
    }
}
