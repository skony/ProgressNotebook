package pl.progressnotebook.recyclerviews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.piotrek.progressnotebook.R;

import pl.progressnotebook.models.Exercise;

/**
 * Created by piotr on 09.03.16.
 */
public class RecyclerViewExercisesAdapter extends RecyclerView.Adapter<RecyclerViewExercisesAdapter.ViewHolder> {

    private Exercise[] mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextViewName;
        /*public TextView mTextViewSeries;
        public TextView mTextViewLoads;
        public TextView mTextViewRepeats;*/

        public ViewHolder(View v) {
            super(v);
            mTextViewName = (TextView) v.findViewById(R.id.exercise_name);
           /* mTextViewLoad = (TextView) v.findViewById(R.id.exercise_load);
            mTextViewRepeats = (TextView) v.findViewById(R.id.exercise_repeats);*/
        }
    }

    public RecyclerViewExercisesAdapter(Exercise[] dataSet){
        mDataSet = dataSet;
    }

    @Override
    public RecyclerViewExercisesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewExercisesAdapter.ViewHolder holder, int position) {
        holder.mTextViewName.setText( mDataSet[position].getName() );
        /*holder.mTextViewSeries = mDataSet[position].getSeries();
        holder.mTextViewLoads = mDataSet[position].getLoads();*/
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
