package pl.progressnotebook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.piotrek.progressnotebook.R;

import pl.progressnotebook.models.Category;

/**
 * Created by piotr on 12.04.16.
 */
public class CategoryAdapter extends BaseAdapter {

    private Context mContext;
    private static LayoutInflater mInflater = null;
    private Category[] mDataSet;

    public CategoryAdapter(Context mContext, Category[] mDataSet) {
        this.mContext = mContext;
        this.mDataSet = mDataSet;
        mInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSet.length;
    }

    @Override
    public Object getItem(int position) {
        return mDataSet[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;

        if(convertView == null) {
            v = mInflater.inflate(R.layout.searched_element, null);
        }
        else {
            v = convertView;
        }

        TextView textName = (TextView) v.findViewById(R.id.element_name_text);
        TextView textId = (TextView) v.findViewById(R.id.element_id_text);

        textName.setText(mDataSet[position].getName());
        textId.setText( Integer.toString(mDataSet[position].getId()) );

        return v;
    }
}
