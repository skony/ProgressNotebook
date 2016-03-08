package pl.progressnotebook.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.piotrek.progressnotebook.R;

/**
 * Created by piotr on 05.03.16.
 */
public class NewExerciseFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_new_exercise, container, false);
        return v;
    }
}
