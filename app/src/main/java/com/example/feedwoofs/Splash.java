package com.example.feedwoofs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.HashMap;


public class Splash extends AppCompatActivity {

    private CardView card;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);

        TextView namaLogin = findViewById(R.id.welcome);

        sessionManager = new SessionManager(this);
        HashMap<String,String> user = sessionManager.getUserDetail();
        String fullName = user.get(sessionManager.FULLNAME);

        namaLogin.setText("Hi "+fullName);

        card = (CardView) findViewById(R.id.cardView);
        Animation layout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        card.startAnimation(layout);

        Thread timer = new Thread(){

            @Override
            public void run(){
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),HomeActivity1.class);
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
