package pl.progressnotebook.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.example.piotrek.progressnotebook.R;

/**
 * Created by piotr on 30.03.16.
 */
public class SearchExerciseActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_exercise);
    }
}
