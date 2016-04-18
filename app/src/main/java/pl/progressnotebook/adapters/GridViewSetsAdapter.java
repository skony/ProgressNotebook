package pl.progressnotebook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.piotrek.progressnotebook.R;

import pl.progressnotebook.globals.Globals;
import pl.progressnotebook.models.WorkoutSet;

/**
 * Created by piotr on 26.02.16.
 */
public class GridViewSetsAdapter extends BaseAdapter{

    private Context mContext;
    private WorkoutSet[] setsList;

    public GridViewSetsAdapter(Context c, WorkoutSet[] setsList) {
        mContext = c;
        this.setsList = setsList;
    }

    public void setSetsList(WorkoutSet[] setsList) {
        this.setsList = setsList;
    }

    @Override
    public int getCount() {

        return setsList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(mContext);

            gridView = inflater.inflate(R.layout.workout_set, null);

            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_text);

            String nameOfSet = setsList[position].getName();

            if (nameOfSet.equals(Globals.RECOVERY_NAME)) {
                imageView.setImageResource(R.drawable.ic_weekend_black_24dp);
                textView.setText(nameOfSet);
                gridView.setTag(Globals.RECOVERY_NAME);
            } else if (nameOfSet.equals(Globals.NEW_SET_NAME)) {
                imageView.setImageResource(R.drawable.ic_fitness_center_black_24dp);
                textView.setText(nameOfSet);
                gridView.setTag(Globals.NEW_SET_NAME);
            } else if (!nameOfSet.isEmpty()) {
                imageView.setImageResource(R.drawable.ic_fitness_center_black_24dp);
                textView.setText(nameOfSet);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
