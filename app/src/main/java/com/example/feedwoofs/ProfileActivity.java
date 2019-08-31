package com.example.feedwoofs;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;


public class ProfileActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#555424'>Profil </font>"));
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }

    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }
}
