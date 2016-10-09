package com.android.akshitgupta.capstoneproject.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.akshitgupta.capstoneproject.R;
import com.android.akshitgupta.capstoneproject.object.DailyPrediction;

import java.util.List;

/**
 * Created by akshitgupta on 09/10/16.
 */

public class DailyPredictionRecyclerViewAdapter extends RecyclerView.Adapter<DailyPredictionRecyclerViewAdapter.MyViewHolder> {

    private List<DailyPrediction> dailyPredictions;
    private Context context;
    private DailyPredictionRecyclerViewAdapter adapter;
    private SharedPreferences prefs;

    public DailyPredictionRecyclerViewAdapter(List<DailyPrediction> dailyPredictions, Context context) {
        this.dailyPredictions = dailyPredictions;
        this.context = context;
        this.adapter = this;
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_daily_prediction, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DailyPrediction dailyPrediction = dailyPredictions.get(position);
        holder.dailyTitleView.setText(dailyPrediction.getTitle());
        holder.dailyDescriptionView.setText(dailyPrediction.getDescription());

    }

    @Override
    public int getItemCount() {
        return dailyPredictions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dailyTitleView, dailyDescriptionView;

        public MyViewHolder(View view) {
            super(view);
            dailyTitleView = (TextView) view.findViewById(R.id.daily_title);
            dailyDescriptionView = (TextView) view.findViewById(R.id.daily_desc);
        }
    }
}