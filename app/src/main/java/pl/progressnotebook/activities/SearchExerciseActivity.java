package pl.progressnotebook.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.piotrek.progressnotebook.R;

import pl.progressnotebook.api.ApiManager;
import pl.progressnotebook.globals.Globals;

/**
 * Created by piotr on 11.04.16.
 */
public class SearchExerciseActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_exercise);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //doMySearch(query);
        }
        else if(intent.getExtras().getString(Globals.CATEGORY_ID_NAME) != null) {

        }
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {
            String getResult = ApiManager.GET(urls[0]);
            //mDataSet = ApiManager.parseCategoryFromJson(getResult);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            //etResponse.setText(result);
            //CategoryAdapter adapter = new CategoryAdapter(ChooseCategoryActivity.this, mDataSet);
            //mListView.setAdapter(adapter);
        }
    }
}
