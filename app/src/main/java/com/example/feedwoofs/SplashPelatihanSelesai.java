package com.example.feedwoofs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class SplashPelatihanSelesai extends AppCompatActivity {

    private CardView card;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_selesai);

        card = (CardView) findViewById(R.id.cardView);
        Animation layout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        card.startAnimation(layout);

        Thread timer = new Thread(){

            @Override
            public void run(){
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),HomeActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    { }
}
