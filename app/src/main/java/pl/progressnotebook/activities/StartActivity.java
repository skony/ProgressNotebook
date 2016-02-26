package pl.progressnotebook.activities;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.piotrek.progressnotebook.R;

import java.util.Calendar;

import pl.progress.notebook.gridviews.GridViewSetsAdapter;
import pl.progressnotebook.activities.fragments.DatePickerFragment;

public class StartActivity extends AppCompatActivity {

    private String[] WORKOUT_SETS = new String[] {
            "recovery", "new set" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setDefaultDate();

        GridView gridview = (GridView) findViewById(R.id.workout_sets_gridview);
        gridview.setAdapter(new GridViewSetsAdapter(this, WORKOUT_SETS));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                String tag = (String) v.getTag();

                if(tag.equals("recovery")){
                    Intent intent = new Intent(getApplicationContext(), RecoveryActivity.class);
                    startActivity(intent);
                }
            }
        });

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
