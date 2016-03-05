package pl.progressnotebook.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;

import com.example.piotrek.progressnotebook.R;

public class RecoveryActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        NumberPicker np = (NumberPicker) findViewById(R.id.number_picker);
        np.setMinValue(600);
        np.setMaxValue(1200);
        np.setWrapSelectorWheel(false);
        np.setFormatter(new NumberPicker.Formatter() {

                            @Override
                            public String format(int i) {

                                double realValue = ((double) i ) / 10;
                                return String.valueOf(realValue);
                            }
                        }
        );
    }
}