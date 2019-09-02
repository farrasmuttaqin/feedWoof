package com.example.feedwoofs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class ListAhliPakanActivity extends AppCompatActivity  {

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private Button chat,detil, order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list_ahlipakan);
        super.onCreate(savedInstanceState);

        BottomNavigationView mBtmView = (BottomNavigationView) findViewById(R.id.btm_nav);
        mBtmView.getMenu().findItem(R.id.action_home).setChecked(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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

        detil = (Button) findViewById(R.id.detil);
        detil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail();
            }
        });

        chat = (Button) findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChat();
            }
        });

        order = (Button) findViewById(R.id.order1);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrder();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    protected void openChat(){
        Intent intent = new Intent(ListAhliPakanActivity.this,ChatActivity.class);
        startActivity(intent);
    }

    protected void openDetail(){
        Intent intent = new Intent(ListAhliPakanActivity.this,ProfileActivity.class);
        startActivity(intent);
    }

    protected void openProfile(){
        Intent intent = new Intent(ListAhliPakanActivity.this,ProfileUserActivity.class);
        startActivity(intent);
    }

    protected void openBeranda(){
        Intent intent = new Intent(this,HomeActivity2.class);
        startActivity(intent);
    }

    protected void openOrder(){
        Intent intent = new Intent(this,OrderActivity.class);
        startActivity(intent);
    }

    protected void openPesanan(){
        Intent intent = new Intent(this,PesananActivity.class);
        startActivity(intent);
    }
}
