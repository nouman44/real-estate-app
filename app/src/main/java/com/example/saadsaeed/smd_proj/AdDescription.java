package com.example.saadsaeed.smd_proj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdDescription extends AppCompatActivity {


    ImageView i;
    TextView t;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    Bitmap image =null;
    String title=null;
    String description=null;
    String price=null;
    String date=null;
    String name=null;
    String phoneno=null;
    String location=null;
    Double longitude=null;
    Double latitude=null;
    String mapName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_description);
        Intent intent = getIntent();
        image = (Bitmap) intent.getParcelableExtra("pic");
        title=getIntent().getExtras().getString("title");
        description=getIntent().getExtras().getString("description");
        price=getIntent().getExtras().getString("price");
        date=getIntent().getExtras().getString("date");
        name=getIntent().getExtras().getString("name");
        phoneno=getIntent().getExtras().getString("phoneNo");
        location=getIntent().getExtras().getString("location");
        longitude=getIntent().getExtras().getDouble("longitude");
        latitude=getIntent().getExtras().getDouble("latitude");
        mapName=getIntent().getExtras().getString("mapName");


        ImageView i = (ImageView) findViewById(R.id.imageView25);
        TextView t = (TextView) findViewById(R.id.textView36);
        TextView t1 = (TextView) findViewById(R.id.textView38);
        TextView t2 = (TextView) findViewById(R.id.textView39);
        TextView t3 = (TextView) findViewById(R.id.textView40);
        TextView t4 = (TextView) findViewById(R.id.textView41);
        TextView t5 = (TextView) findViewById(R.id.textView36);
        TextView t6 = (TextView) findViewById(R.id.textView37);


        i.setImageBitmap(image);

        t.setText("Title= " + title);
        t1.setText("Price= " + price);
        t2.setText("Date= " + date);
        t3.setText("Name of Owner= " + name);
        t4.setText("Phone Number= " + phoneno);
        t5.setText("Location= " + location);
        t6.setText(description);

    }

    public void openMap(View view){
        String geoUri = "http://maps.google.com/maps?q=loc:" + latitude + "," +  longitude + " (" + mapName + ")";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
        startActivity(intent);
    }
}