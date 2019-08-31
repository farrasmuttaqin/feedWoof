package com.example.feedwoofs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ProfileUserActivity extends AppCompatActivity  {

    private BottomNavigationView bottomNavigationView,mBtmView;
    private Toolbar toolbar;
    private Button profil;

    private LinearLayout profile_anda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile_user);

        super.onCreate(savedInstanceState);

        mBtmView = (BottomNavigationView) findViewById(R.id.btm_nav);
        mBtmView.getMenu().findItem(R.id.action_profile).setChecked(true);

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profil Saya");

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.btm_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.action_home :
                        openBeranda();
                        break;
                    case R.id.action_pesanan :
                        openPesanan();
                        break;
                    case R.id.action_profile :
                        openProfile();
                        break;
                }

                return true;
            }
        });

        profile_anda = (LinearLayout) findViewById(R.id.profile_anda);
        profile_anda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileAnda();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    protected void openProfile(){
        Intent intent = new Intent(this,ProfileUserActivity.class);
        startActivity(intent);
    }

    protected void openProfileAnda(){
        Intent intent = new Intent(this,DataDiriUserActivity.class);
        startActivity(intent);
    }

    protected void openBeranda(){
        Intent intent = new Intent(this,HomeActivity2.class);
        startActivity(intent);
    }

    protected void openPesanan(){
        Intent intent = new Intent(this,PesananActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this,HomeActivity2.class);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
