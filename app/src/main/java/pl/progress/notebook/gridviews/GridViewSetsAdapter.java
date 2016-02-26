package pl.progress.notebook.gridviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.piotrek.progressnotebook.R;

import pl.progressnotebook.activities.StartActivity;

/**
 * Created by piotr on 26.02.16.
 */
public class GridViewSetsAdapter extends BaseAdapter{

    private Context mContext;
    private String[] setsList;
    String RECOVERY = "recovery";
    String NEW_SET = "new_set";

    public GridViewSetsAdapter(Context c, String[] setsList) {
        mContext = c;
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

            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String setName = setsList[position];

            if (setName.equals("recovery")) {
                imageView.setImageResource(R.drawable.ic_weekend_black_24dp);
                gridView.setTag(RECOVERY);
            } else if (setName.equals("new set")) {
                imageView.setImageResource(R.drawable.ic_fitness_center_black_24dp);
                gridView.setTag(NEW_SET);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
