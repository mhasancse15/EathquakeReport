package com.mhasancse15.eathquakereport.adepter;

import android.app.Activity;
import android.content.Context;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mhasancse15.eathquakereport.R;
import com.mhasancse15.eathquakereport.data.Earthquake;

import java.util.ArrayList;

public class EarthquakeAdepter extends ArrayAdapter<Earthquake>{

    private Activity activity;
    private ArrayList<Earthquake> earthquakeArrayList = new ArrayList<>();
    private int layoutResources;


    public EarthquakeAdepter(@NonNull Activity mActivity, @LayoutRes int resource, ArrayList<Earthquake> mEarthquakes) {
        super(mActivity, resource,mEarthquakes);

        activity = mActivity;
        earthquakeArrayList = mEarthquakes;
        layoutResources = resource;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return earthquakeArrayList.size();
    }

    @Nullable
    @Override
    public Earthquake getItem(int position) {
        return earthquakeArrayList.get(position);
    }

    @Override
    public int getPosition(@Nullable Earthquake item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;


        if (row == null || (row.getTag()) == null ){

            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResources,null);
            holder = new ViewHolder();

            holder.magnitudeTextView = row.findViewById(R.id.magnitudeText);
            holder.locationTextView = row.findViewById(R.id.locationText);
            holder.dateTextView = row.findViewById(R.id.dateText);

            row.setTag(holder);
        }else {
            holder = (ViewHolder) row.getTag();
        }

        holder.earthquake = getItem(position);
        holder.magnitudeTextView.setText(holder.earthquake.getmMagnitude());
        holder.locationTextView.setText(holder.earthquake.getmLocationName());
        holder.dateTextView.setText(holder.earthquake.getmDate());

        return row;
    }

    class ViewHolder{

        private Earthquake earthquake;
        private TextView magnitudeTextView;
        private TextView locationTextView;
        private TextView dateTextView;
    }
}
