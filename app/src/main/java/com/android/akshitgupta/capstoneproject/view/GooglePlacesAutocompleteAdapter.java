package com.android.akshitgupta.capstoneproject.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.android.akshitgupta.capstoneproject.object.GeoDetails;
import com.android.akshitgupta.capstoneproject.task.GeoPlacesAutoCompleteTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by akshitgupta on 02/10/16.
 */

public class GooglePlacesAutocompleteAdapter extends ArrayAdapter<GeoDetails> implements Filterable {
    private ArrayList<GeoDetails> resultList;

    public GooglePlacesAutocompleteAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public GeoDetails getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    // Retrieve the autocomplete results.

                    resultList = autocomplete(constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    public static ArrayList<GeoDetails> autocomplete(String input) {
        ArrayList<GeoDetails> resultList = null;
        GeoPlacesAutoCompleteTask task;
        task = new GeoPlacesAutoCompleteTask();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, input);
        else
            task.execute(input);

        try {
            resultList = task.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}


