package pl.progressnotebook.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piotrek.progressnotebook.R;

import java.util.List;

import pl.progressnotebook.adapters.CategoryAdapter;
import pl.progressnotebook.api.ApiManager;
import pl.progressnotebook.models.Category;

/**
 * Created by piotr on 30.03.16.
 */
public class ChooseCategoryActivity extends AppCompatActivity {

    private EditText etResponse;
    private TextView tvIsConnected;
    private ListView mListView;
    private Category[] mDataSet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        etResponse = (EditText) findViewById(R.id.etResponse);
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
                b.putString("category_id", itemId);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_exercise_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private class HttpAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... urls) {
            String getResult = ApiManager.GET("https://wger.de/api/v2/exercisecategory.json/");
            mDataSet = ApiManager.parseJson(getResult);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            //etResponse.setText(result);
            CategoryAdapter adapter = new CategoryAdapter(ChooseCategoryActivity.this, mDataSet);
            mListView.setAdapter(adapter);
        }
    }
}
