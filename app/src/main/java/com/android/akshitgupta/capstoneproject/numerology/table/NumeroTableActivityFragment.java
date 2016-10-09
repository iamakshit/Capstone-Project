package com.android.akshitgupta.capstoneproject.numerology.table;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.akshitgupta.capstoneproject.R;
import com.android.akshitgupta.capstoneproject.dailyprediction.DailyPredictionActivity;
import com.android.akshitgupta.capstoneproject.object.NumeroPrediction;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NumeroTableActivityFragment extends Fragment {

    public NumeroTableActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        NumeroTableActivity numeroTableActivity = (NumeroTableActivity) getActivity();
        List<NumeroPrediction> numeroPredictionList = numeroTableActivity.getNumeroPredictionList();
        return inflater.inflate(R.layout.fragment_numero_table, container, false);
    }
}
