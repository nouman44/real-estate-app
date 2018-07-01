package com.example.saadsaeed.smd_proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import java.text.BreakIterator;

public class Searchbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);




        SearchView simpleSearchView = (SearchView) findViewById(R.id.searchView); // inititate a search view
        CharSequence query = simpleSearchView.getQuery(); // get the query string currently in the text field





    }
}
