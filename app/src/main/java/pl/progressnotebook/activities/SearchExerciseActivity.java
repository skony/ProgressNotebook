package pl.progressnotebook.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piotrek.progressnotebook.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import pl.progressnotebook.api.ApiManager;

/**
 * Created by piotr on 30.03.16.
 */
public class SearchExerciseActivity extends AppCompatActivity {

    private EditText etResponse;
    private TextView tvIsConnected;
    private ListView mListView;
    private List<String> mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_exercise);
        etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_exercise_toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.exercises_list);

        if (ApiManager.isConnected(getApplicationContext())) {
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else {
            tvIsConnected.setText("You are NOT conncted");
        }

        new HttpAsyncTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_exercise_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private class HttpAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... urls) {
            String getResult = ApiManager.GET("https://wger.de/api/v2/exercisecategory.json/");
            mData = ApiManager.parseJson(getResult);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            //etResponse.setText(result);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchExerciseActivity.this, R.layout.searched_element, mData);
            mListView.setAdapter(adapter);
        }
    }
}
