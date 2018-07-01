package com.example.saadsaeed.smd_proj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

public class AccountSettings extends AppCompatActivity {

    private TextView txtName;
    private TextView txtEmail;
    private TextView txtPhone;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        txtName = (TextView) findViewById(R.id.textView20);
        txtEmail = (TextView) findViewById(R.id.textView21);
        txtPhone = (TextView) findViewById(R.id.textView22);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").orderByChild("email").equalTo(FirebaseAuth.getInstance().getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                User user = dataSnapshot.getValue(User.class);
                txtName.setText(user.username);
                txtEmail.setText(user.email);
                txtPhone.setText(user.phoneno);
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

    public void onclick (View v)
    {
       if(FirebaseAuth.getInstance().getCurrentUser()!=null)
       {
           AccessToken accessToken = AccessToken.getCurrentAccessToken();
           if(accessToken != null)
           {
               FirebaseAuth.getInstance().signOut();
               LoginManager.getInstance().logOut();
           }

           Intent i= new Intent(this,MainActivity.class);
           startActivity(i);

       }


    }
}
