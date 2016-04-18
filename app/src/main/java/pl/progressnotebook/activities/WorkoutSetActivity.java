package pl.progressnotebook.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.piotrek.progressnotebook.R;

import pl.progressnotebook.db.AppDbHelper;
import pl.progressnotebook.db.DbTools;
import pl.progressnotebook.models.Exercise;

import static pl.progressnotebook.db.DbContract.*;

/**
 * Created by piotr on 05.03.16.
 */
public class WorkoutSetActivity extends AppCompatActivity {

    private String nameOfSet;
    private EditText mEditText;
    private SQLiteDatabase db;
    private AppDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_set);

        dbHelper = new AppDbHelper(this);
        db = dbHelper.getWritableDatabase();

        Bundle bundle = getIntent().getExtras();
        nameOfSet = bundle!=null ? bundle.getString("name_of_set") : "";
        mEditText = (EditText) findViewById(R.id.workout_set_name);
        mEditText.setText(nameOfSet);
    }

    public void startNewExerciseSearching(View v){
        Intent intent = new Intent(getApplicationContext(), ChooseCategoryActivity.class);
        startActivity(intent);
    }

    public void saveWorkoutSet(View v){
        String currentNameOfSet =  mEditText.getText().toString();

        if(nameOfSet.isEmpty() && !currentNameOfSet.isEmpty()) {
            long newRowId = DbTools.insert(db,
                    WorkoutSets.TABLE_NAME,
                    WorkoutSets.COLUMN_NAME_NAME,
                    currentNameOfSet
            );
        }
        else if (!nameOfSet.isEmpty() && currentNameOfSet.isEmpty()) {
            DbTools.delete(db,
                    WorkoutSets.TABLE_NAME,
                    WorkoutSets.COLUMN_NAME_NAME,
                    nameOfSet
            );
        }
        else if(!nameOfSet.isEmpty() && !currentNameOfSet.isEmpty()
                && !nameOfSet.equals(currentNameOfSet)){
            DbTools.update(db,
                    WorkoutSets.TABLE_NAME,
                    WorkoutSets.COLUMN_NAME_NAME,
                    nameOfSet,
                    currentNameOfSet
            );
        }

        finish();
    }

    public void initDataSet(){

    }
}
