package pl.progressnotebook.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.piotrek.progressnotebook.R;

import pl.progressnotebook.activities.fragments.NewExerciseFragment;
import pl.progressnotebook.db.AppDbHelper;
import pl.progressnotebook.db.DbContract;
import pl.progressnotebook.models.Exercise;
import pl.progressnotebook.recyclerviews.RecyclerViewExercisesAdapter;

import static pl.progressnotebook.db.DbContract.*;

/**
 * Created by piotr on 05.03.16.
 */
public class WorkoutSetActivity extends AppCompatActivity {

    private int id;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Exercise[] mDataSet;
    private SQLiteDatabase db;
    private AppDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_set);

        mRecyclerView = (RecyclerView) findViewById(R.id.exercises_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mDataSet = new Exercise[]{};
        mAdapter = new RecyclerViewExercisesAdapter(mDataSet);
        mRecyclerView.setAdapter(mAdapter);

        dbHelper = new AppDbHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    public void showNewExerciseDialog(View v){
        DialogFragment newFragment = new NewExerciseFragment();
        newFragment.show(getSupportFragmentManager(), "newExercise");
    }

    public void saveWorkoutSet(View v){
        EditText editText = (EditText) findViewById(R.id.workout_set_name);
        ContentValues values = new ContentValues();
        values.put(DbContract.WorkoutSets.COLUMN_NAME_NAME, editText.getText().toString());

        long newRowId;
        newRowId = db.insert(
                DbContract.WorkoutSets.TABLE_NAME,
                null,
                values);

        finish();
    }

    public void initDataSet(){

    }
}
