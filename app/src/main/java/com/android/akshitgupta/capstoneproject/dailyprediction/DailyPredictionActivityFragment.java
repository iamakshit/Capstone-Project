package com.android.akshitgupta.capstoneproject.dailyprediction;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.akshitgupta.capstoneproject.R;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DailyPredictionActivityFragment extends Fragment {
    public static String TAG = DailyPredictionActivityFragment.class.getSimpleName();

    public DailyPredictionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DailyPredictionActivity dailyPredictionActivity = (DailyPredictionActivity) getActivity();
        List<String> dailyPredictionList = dailyPredictionActivity.getDailyPredictionList();
        Log.i(TAG,"dailyPredictionList size ="+dailyPredictionList.size());
        return inflater.inflate(R.layout.fragment_daily_prediction, container, false);
    }

    
}
