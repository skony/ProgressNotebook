package pl.progressnotebook.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piotrek.progressnotebook.R;

import pl.progressnotebook.api.ApiManager;

/**
 * Created by piotr on 30.03.16.
 */
public class SearchExerciseActivity extends ListActivity {

    public EditText etResponse;
    public TextView tvIsConnected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_exercise);
        etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        if (ApiManager.isConnected(getApplicationContext())) {
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else {
            tvIsConnected.setText("You are NOT conncted");
        }

        new HttpAsyncTask().execute("http://hmkcode.appspot.com/rest/controller/get.json");
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            return ApiManager.GET(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            etResponse.setText(result);
        }
    }
}
