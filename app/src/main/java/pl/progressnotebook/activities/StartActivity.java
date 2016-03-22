package pl.progressnotebook.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.piotrek.progressnotebook.R;

import java.util.Calendar;

import pl.progressnotebook.db.AppDbHelper;
import pl.progressnotebook.db.DbContract;
import pl.progressnotebook.gridviews.GridViewSetsAdapter;
import pl.progressnotebook.activities.fragments.DatePickerFragment;
import pl.progressnotebook.models.WorkoutSet;

public class StartActivity extends AppCompatActivity {


    private SQLiteDatabase db;
    private AppDbHelper dbHelper;
    private WorkoutSet[] mDataSet;
    private WorkoutSet RECOVERY;
    private WorkoutSet NEW_SET;
    private final int DEFAULT_SETS_NUM = 2;
    private GridView mGridView;
    private GridViewSetsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setDefaultDate();

        dbHelper = new AppDbHelper(this);
        db = dbHelper.getWritableDatabase();
        initDataSet();

        mGridView = (GridView) findViewById(R.id.workout_sets_gridview);
        mAdapter = new GridViewSetsAdapter(this, mDataSet);
        mGridView.setAdapter(mAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                String nameOfSet = ((TextView) v.findViewById(R.id.grid_item_text)).getText().toString();

                if (nameOfSet.equals(RECOVERY.getName())) {
                    Intent intent = new Intent(getApplicationContext(), RecoveryActivity.class);
                    startActivity(intent);
                } else if (nameOfSet.equals(NEW_SET.getName())) {
                    Intent intent = new Intent(getApplicationContext(), WorkoutSetActivity.class);
                    startActivity(intent);
                } else if (!nameOfSet.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), WorkoutSetActivity.class);
                    Bundle b = new Bundle();
                    b.putString("name_of_set", nameOfSet);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });

        Toolbar startToolbar = (Toolbar) findViewById(R.id.start_toolbar);
        setSupportActionBar(startToolbar);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        initDataSet();
        mAdapter = new GridViewSetsAdapter(this, mDataSet);
        mGridView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_toolbar, menu);
        return true;
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

    public void initDataSet(){
        String[] projection = {
                DbContract.WorkoutSets._ID,
                DbContract.WorkoutSets.COLUMN_NAME_NAME,
        };

        Cursor cursor = db.query(
                DbContract.WorkoutSets.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        int setsNum = cursor.getCount();
        RECOVERY = new WorkoutSet(0, "Recovery");
        NEW_SET = new WorkoutSet(1, "New Set");
        mDataSet = new WorkoutSet[setsNum + DEFAULT_SETS_NUM];

        if(setsNum == 0){
            mDataSet[0] = RECOVERY;
            mDataSet[1] = NEW_SET;
        }
        else{
            mDataSet[0] = RECOVERY;
            mDataSet[1] = NEW_SET;
            cursor.moveToFirst();

            for(int i=DEFAULT_SETS_NUM; i<setsNum+DEFAULT_SETS_NUM; i++){
                WorkoutSet workoutSet = new WorkoutSet();
                workoutSet.setId( cursor.getLong( cursor.getColumnIndexOrThrow( DbContract.WorkoutSets._ID ) ) );
                workoutSet.setName( cursor.getString( cursor.getColumnIndexOrThrow( DbContract.WorkoutSets.COLUMN_NAME_NAME ) ) );
                mDataSet[i] = workoutSet;
                cursor.moveToNext();
            }
        }
    }
}
