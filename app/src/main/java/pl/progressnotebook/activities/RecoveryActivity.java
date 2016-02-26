package pl.progressnotebook.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.piotrek.progressnotebook.R;

/**
 * Created by piotr on 26.02.16.
 */
public class RecoveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);
        Log.d("SOMETHING", "Content");
    }
}
