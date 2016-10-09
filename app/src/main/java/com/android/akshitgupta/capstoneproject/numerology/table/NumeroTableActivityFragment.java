package com.android.akshitgupta.capstoneproject.numerology.table;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.akshitgupta.capstoneproject.R;
import com.android.akshitgupta.capstoneproject.adapter.NumeroTableRecyclerViewAdapter;
import com.android.akshitgupta.capstoneproject.object.NumeroPrediction;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NumeroTableActivityFragment extends Fragment {

    NumeroTableRecyclerViewAdapter adapter;

    public NumeroTableActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        NumeroTableActivity numeroTableActivity = (NumeroTableActivity) getActivity();
        List<NumeroPrediction> numeroPredictions = numeroTableActivity.getNumeroPredictionList();
        View view = inflater.inflate(R.layout.fragment_daily_prediction_list, container, false);

        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new NumeroTableRecyclerViewAdapter(numeroPredictions, getContext());
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}
