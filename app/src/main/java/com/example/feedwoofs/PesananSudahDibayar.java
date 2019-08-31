package com.example.feedwoofs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class PesananSudahDibayar extends AppCompatActivity {

    private TextView pembayaran1,pembayaran2,pembayaran3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_sudah_dibayar);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#555424'>Detail Pesanan</font>"));

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        pembayaran1 = (TextView) findViewById(R.id.pembayaran1);

        pembayaran1.setText(Html.fromHtml("<b>BCA</b>"));


    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this,PesananActivity.class);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
