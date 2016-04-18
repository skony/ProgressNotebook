package pl.progressnotebook.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piotrek.progressnotebook.R;

import pl.progressnotebook.adapters.CategoryAdapter;
import pl.progressnotebook.api.ApiManager;
import pl.progressnotebook.globals.Globals;
import pl.progressnotebook.models.Category;

/**
 * Created by piotr on 30.03.16.
 */
public class ChooseCategoryActivity extends AppCompatActivity {

    private TextView tvIsConnected;
    private ListView mListView;
    private Category[] mDataSet;
    private static final String CATEGORIES_FROM_API = "https://wger.de/api/v2/exercisecategory.json/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        Toolbar toolbar = (Toolbar) findViewById(R.id.choose_category_toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.categories_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemId = (String) ((TextView) view.findViewById( R.id.element_id_text )).getText();
                Intent intent = new Intent(getApplicationContext(), SearchExerciseActivity.class);
                Bundle b = new Bundle();
                b.putString(Globals.CATEGORY_ID_NAME, itemId);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

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
        getMenuInflater().inflate(R.menu.menu_search_exercise_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private class HttpAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... urls) {
            String getResult = ApiManager.GET(CATEGORIES_FROM_API);
            mDataSet = ApiManager.parseCategoryFromJson(getResult);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            CategoryAdapter adapter = new CategoryAdapter(ChooseCategoryActivity.this, mDataSet);
            mListView.setAdapter(adapter);
        }
    }
}
