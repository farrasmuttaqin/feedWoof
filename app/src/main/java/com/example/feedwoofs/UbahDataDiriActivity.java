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
import android.widget.Toast;

public class UbahDataDiriActivity extends AppCompatActivity  {

    private BottomNavigationView bottomNavigationView,mBtmView;
    private Toolbar toolbar;
    private Button masuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_ubah_data_diri);

        super.onCreate(savedInstanceState);

        mBtmView = (BottomNavigationView) findViewById(R.id.btm_nav);
        mBtmView.getMenu().findItem(R.id.action_profile).setChecked(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#555424'>Data Diri</font>"));

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

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

        masuk = (Button) findViewById(R.id.masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanProfile();
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

    protected void openBeranda(){
        Intent intent = new Intent(this,HomeActivity2.class);
        startActivity(intent);
    }

    protected void simpanProfile(){
        Intent intent = new Intent(this,SplashProfileBerhasil.class);
        startActivity(intent);
    }

    protected void openPesanan(){
        Intent intent = new Intent(this,PesananActivity.class);
        startActivity(intent);
    }
}
