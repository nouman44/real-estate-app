package com.example.saadsaeed.smd_proj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.PlaceDetectionClient;

public class PostActivity extends AppCompatActivity {

    private EditText txtTitle;
    private EditText txtValue;
    private EditText txtLocation;
    private EditText txtDescription;
    private Bitmap picture;
    private EditText txtName;
    private EditText txtPhone;
    DatabaseReference mDatabase;
    Tracker mTracker;
    int PLACE_PICKER_REQUEST = 2;
    Place p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        txtTitle = (EditText) findViewById(R.id.editText);
        txtValue = (EditText) findViewById(R.id.editText2);
        txtLocation = (EditText) findViewById(R.id.editText3);
        txtDescription = (EditText) findViewById(R.id.editText5);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        txtName = (EditText) findViewById(R.id.editText14);
        txtPhone = (EditText) findViewById(R.id.editText11);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.i(TAG, "Setting screen name: " + name);
        mTracker.setScreenName("Image~" + "Post Add");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());
    }

    public void onPostClick(View view) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        picture.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Ad ad = new Ad(txtTitle.getText().toString(), txtValue.getText().toString(), txtDescription.getText().toString(), imageEncoded, FirebaseAuth.getInstance().getUid(), date, p.getLatLng().latitude, p.getLatLng().longitude, p.getName().toString(), txtName.getText().toString(), txtPhone.getText().toString(),txtLocation.getText().toString());
        mDatabase.child("users").child(FirebaseAuth.getInstance().getUid()).child("ads").push().setValue(ad);
        mDatabase.child("adsto").push().setValue(ad);
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    public void takePhoto(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            picture = imageBitmap;
            ImageView myImageView = (ImageView) findViewById(R.id.imageView15);
            myImageView.setImageBitmap(imageBitmap);
        }

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getName());
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", place.getLatLng().latitude, place.getLatLng().longitude);
                String geoUri = "http://maps.google.com/maps?q=loc:" + place.getLatLng().latitude + "," + place.getLatLng().longitude + " (" + place.getName() + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                //startActivity(intent);
                Toast.makeText(PostActivity.this, toastMsg, Toast.LENGTH_LONG).show();
                p = place;
                txtLocation.setText(p.getAddress().toString());
            }
        }

    }

    public void detectLocation(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


}
