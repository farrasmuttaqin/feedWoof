package com.example.feedwoofs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView register;
    private Button tanpaDaftar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        register = (TextView) findViewById(R.id.daftar);
        tanpaDaftar = (Button) findViewById(R.id.tanpaDaftar);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRegister();
            }
        });

        tanpaDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTanpaDaftar();
            }
        });

    }

    protected void openRegister(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    protected void openTanpaDaftar(){
        Intent intent = new Intent(this,HomeActivity2.class);
        startActivity(intent);
    }
}
