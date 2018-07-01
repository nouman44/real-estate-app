package com.example.saadsaeed.smd_proj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AdDescription1 extends AppCompatActivity {

    DatabaseReference mDatabase;
    Bitmap image = null;
    String title = null;
    String description = null;
    String price = null;
    String date = null;
    String name = null;
    String phoneno = null;
    String location = null;
    Double longitude = null;
    Double latitude = null;
    String mapName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_description1);
        Intent intent = getIntent();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        image = (Bitmap) intent.getParcelableExtra("pic");
        title = getIntent().getExtras().getString("title");
        description = getIntent().getExtras().getString("description");
        price = getIntent().getExtras().getString("price");
        date = getIntent().getExtras().getString("date");
        name = getIntent().getExtras().getString("name");
        phoneno = getIntent().getExtras().getString("phoneNo");
        location = getIntent().getExtras().getString("location");
        longitude = getIntent().getExtras().getDouble("longitude");
        latitude = getIntent().getExtras().getDouble("latitude");
        mapName = getIntent().getExtras().getString("mapName");


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

    public void vvvvaa(View v) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        Ad ad = new Ad(title, price, description, imageEncoded, FirebaseAuth.getInstance().getUid(), date, latitude, longitude, mapName, name, phoneno, location);
        mDatabase.child("savedads").push().setValue(ad);

    }


    public void onClickVoice(View v) {
        int REQUEST_CODE = 1;
        String DIALOG_TEXT = "Speech recognition demo";
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, DIALOG_TEXT);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, REQUEST_CODE);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        startActivityForResult(intent, 3);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<String> speech;
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            // imageBitmap = (Bitmap) extras.get("data");
            // mImageLabel.setImageBitmap(imageBitmap);


        } else if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String data1 = speech.get(0);
                TextView txt = (TextView) findViewById(R.id.textView24);
                txt.setText(data1);
                //you can set resultSpeech to your EditText or TextView

            }

        }
    }

    public void openMap(View view) {
        String geoUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + mapName + ")";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
        startActivity(intent);
    }

}
