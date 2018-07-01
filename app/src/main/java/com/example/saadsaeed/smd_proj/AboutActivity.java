package com.example.saadsaeed.smd_proj;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.appinvite.AppInviteInvitation;

public class AboutActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        shareDialog = new ShareDialog(this);
    }

    public void ShareClick(View view){
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                    .build();
            shareDialog.show(linkContent);
        }
    }

    public void onclick(View v)
    {
        Intent intent = new AppInviteInvitation.IntentBuilder("Invitation")
                .setMessage("Hi , this is a nice app....")
                .setDeepLink(Uri.parse("https://realestatepro.com"))
                .setCallToActionText("Invitation CTA")
                .build();
        startActivityForResult(intent, 5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 5)
        {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                Toast.makeText(this, requestCode, Toast.LENGTH_SHORT).show();

            }
        }
    }
}
