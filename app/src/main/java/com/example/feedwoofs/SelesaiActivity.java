package com.example.feedwoofs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelesaiActivity extends AppCompatActivity  {

    private BottomNavigationView bottomNavigationView,mBtmView;
    private Toolbar toolbar;
    private Button masuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_selesai);

        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daftar Pelatihan");

        masuk = (Button) findViewById(R.id.masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelesai();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void openSelesai(){
        Intent intent = new Intent(this,SplashPelatihanSelesai.class);
        startActivity(intent);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    { }
}
