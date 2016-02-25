package com.example.piotrek.progressnotebook;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setDefaultDate();
        //Toolbar startToolbar = (Toolbar) findViewById(R.id.start_toolbar);
        //setSupportActionBar(startToolbar);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void setDefaultDate(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        String todayDate = Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
        Button pickDateButton = (Button) findViewById(R.id.pick_date);
        pickDateButton.setText(todayDate);
    }

    public void setPickedDate(int year, int month, int day){
        String pickedDate = Integer.toString(day) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
        Button pickDateButton = (Button) findViewById(R.id.pick_date);
        pickDateButton.setText(pickedDate);
    }
}
