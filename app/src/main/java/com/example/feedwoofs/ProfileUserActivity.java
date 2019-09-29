package com.example.feedwoofs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ProfileUserActivity extends AppCompatActivity  {

    private BottomNavigationView bottomNavigationView,mBtmView;
    private Toolbar toolbar;
    private Button profil;
    ImageView myAvatar;
    TextView myName,myAddress;
    CardView logout;

    private LinearLayout profile_anda;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile_user);

        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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
//                        openProfile();
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



        sessionManager = new SessionManager(this);
        HashMap<String,String> user = sessionManager.getUserDetail();
        String fullName = user.get(sessionManager.FULLNAME);
        String address = user.get(sessionManager.ADDRESS);
        String avatar = user.get(sessionManager.AVATAR);

        myName = findViewById(R.id.myName);
        myAddress = findViewById(R.id.myAddress);
        myAvatar = findViewById(R.id.myAvatar);

        myName.setText(fullName);
        myAddress.setText(address);

        final String images = "http://mylostphone.000webhostapp.com/server/JSON_FEED_WOOF/PeternakAvatar/"+avatar.trim();
        Picasso.with(this).load(images).placeholder(R.drawable.minicircle)
        .error(R.drawable.minicircle).into(myAvatar,new com.squareup.picasso.Callback(){
            @Override
            public void onSuccess(){
                myAvatar.getLayoutParams().height = 120;
                myAvatar.getLayoutParams().width = 120;
//                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),images);
//                roundedBitmapDrawable.setCircular(true);
//                myAvatar.setImageDrawable(roundedBitmapDrawable);
            }

            @Override
            public void onError(){

            }
        });

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
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
        Intent intent = new Intent(this,HomeActivity1.class);
        finish();
        overridePendingTransition( 0, 0);
        startActivity(intent);
        overridePendingTransition( 0, 0);
    }

    protected void openPesanan(){
        Intent intent = new Intent(this,PesananActivity.class);
        finish();
        overridePendingTransition( 0, 0);
        startActivity(intent);
        overridePendingTransition( 0, 0);
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this,HomeActivity1.class);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
