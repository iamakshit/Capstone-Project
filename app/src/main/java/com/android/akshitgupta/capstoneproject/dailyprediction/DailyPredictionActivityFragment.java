package com.android.akshitgupta.capstoneproject.dailyprediction;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.akshitgupta.capstoneproject.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DailyPredictionActivityFragment extends Fragment {

    public DailyPredictionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_prediction, container, false);
    }
}
