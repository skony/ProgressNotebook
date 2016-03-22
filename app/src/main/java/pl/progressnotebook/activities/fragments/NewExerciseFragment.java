package pl.progressnotebook.activities.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.piotrek.progressnotebook.R;

import pl.progressnotebook.db.AppDbHelper;

/**
 * Created by piotr on 05.03.16.
 */
public class NewExerciseFragment extends DialogFragment {

    private AppDbHelper mDbHelper;
    private SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mDbHelper = new AppDbHelper(getActivity().getApplicationContext());
        db = mDbHelper.getWritableDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_exercise, container, false);
        return v;
    }

    public void addNewExercise(View v){
        EditText editText = (EditText) v.findViewById(R.id.new_exercise_name_edit);
        String exerciseName = editText.getText().toString();

    }
}
