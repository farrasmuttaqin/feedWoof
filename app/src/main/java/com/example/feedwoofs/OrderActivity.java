package com.example.feedwoofs;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OrderActivity extends AppCompatActivity {

    private EditText tanggal;
    private Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#555424'>Pendaftaran Pelatihan</font>"));

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        tanggal = (EditText) findViewById(R.id.dateText);

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });

        daftar = (Button) findViewById(R.id.masuk);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSplash();
            }
        });
    }

    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }

    public void showDateTimePicker(){
        final Calendar currentDate = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                currentDate.set(year, monthOfYear, dayOfMonth);

                final Calendar myCalendar = Calendar.getInstance();

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = "EEEE, d MMMM yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                EditText edittext= (EditText) findViewById(R.id.dateText);
                edittext.setText(sdf.format(myCalendar.getTime()));
                //use this date as per your requirement
            }
        };
        DatePickerDialog datePickerDialog = new  DatePickerDialog(OrderActivity.this, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),   currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    protected void openSplash(){
        Intent intent = new Intent(this,SplashPelatihan.class);
        startActivity(intent);
    }
}
