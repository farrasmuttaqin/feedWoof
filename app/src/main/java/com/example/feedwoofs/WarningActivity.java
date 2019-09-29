package com.example.feedwoofs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;


public class WarningActivity extends AppCompatActivity {

    private CardView card;
    TextView batalkan;
    Button daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);

        batalkan = findViewById(R.id.batalkan);
        daftar = findViewById(R.id.daftar);

        if(getIntent().getStringExtra("from").equals("homeActivity2")){
            batalkan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openHomeActivity2();
                }
            });
        }else {
            batalkan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openListAhliPakanActivity2();
                }
            });
        }

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDaftar();
            }
        });
    }

    protected void openHomeActivity2(){
        Intent intent = new Intent(this,HomeActivity2.class);
        startActivity(intent);
    }

    protected void openListAhliPakanActivity2(){
        Intent intent = new Intent(this,ListAhliPakanActivity2.class);
        startActivity(intent);
    }

    protected void openDaftar(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
