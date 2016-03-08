package pl.progressnotebook.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.piotrek.progressnotebook.R;

import pl.progressnotebook.activities.fragments.NewExerciseFragment;

/**
 * Created by piotr on 05.03.16.
 */
public class WorkoutSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_set);
    }

    public void showNewExerciseDialog(View v){
        DialogFragment newFragment = new NewExerciseFragment();
        newFragment.show(getSupportFragmentManager(), "newExercise");
    }
}
