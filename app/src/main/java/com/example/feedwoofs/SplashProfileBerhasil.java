package com.example.feedwoofs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class SplashProfileBerhasil extends AppCompatActivity {

    private CardView card;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_profile_berhasil);

        card = (CardView) findViewById(R.id.cardView);
        Animation layout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        card.startAnimation(layout);

        Thread timer = new Thread(){

            @Override
            public void run(){
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),DataDiriUserActivity.class);
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
}
