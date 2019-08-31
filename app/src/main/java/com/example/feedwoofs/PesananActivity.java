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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PesananActivity extends AppCompatActivity  {

    private BottomNavigationView bottomNavigationView,mBtmView;
    private Toolbar toolbar;
    private Button detail,detail2;
    private TextView selesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pesanan);

        super.onCreate(savedInstanceState);

        mBtmView = (BottomNavigationView) findViewById(R.id.btm_nav);
        mBtmView.getMenu().findItem(R.id.action_pesanan).setChecked(true);

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

        detail = (Button) findViewById(R.id.detil);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail();
            }
        });

        detail2 = (Button) findViewById(R.id.detil2);
        detail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail2();
            }
        });

        selesai = (TextView) findViewById(R.id.selesai);
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelesai();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
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

    protected void openPesanan(){
        Intent intent = new Intent(this,PesananActivity.class);
        startActivity(intent);
    }

    protected void openDetail(){
        Intent intent = new Intent(this,PembayaranActivity.class);
        startActivity(intent);
    }

    protected void openDetail2(){
        Intent intent = new Intent(this,PesananSudahDibayar.class);
        startActivity(intent);
    }

    protected void openSelesai(){
        Intent intent = new Intent(this,SelesaiActivity.class);
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
