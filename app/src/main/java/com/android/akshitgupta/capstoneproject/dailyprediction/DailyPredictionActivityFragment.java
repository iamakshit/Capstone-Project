package com.android.akshitgupta.capstoneproject.dailyprediction;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.akshitgupta.capstoneproject.R;
import com.android.akshitgupta.capstoneproject.adapter.DailyPredictionRecyclerViewAdapter;
import com.android.akshitgupta.capstoneproject.object.DailyPrediction;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DailyPredictionActivityFragment extends Fragment {
    public static String TAG = DailyPredictionActivityFragment.class.getSimpleName();

    DailyPredictionRecyclerViewAdapter adapter;
    public DailyPredictionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DailyPredictionActivity dailyPredictionActivity = (DailyPredictionActivity) getActivity();
        List<DailyPrediction> dailyPredictions = dailyPredictionActivity.getDailyPredictionList();
        Log.i(TAG,"dailyPredictionList size ="+dailyPredictions.size());
        View view = inflater.inflate(R.layout.fragment_daily_prediction_list, container, false);

        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new DailyPredictionRecyclerViewAdapter(dailyPredictions,getContext());
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }
        return view;
        //return inflater.inflate(R.layout.fragment_daily_prediction, container, false);
    }


}
