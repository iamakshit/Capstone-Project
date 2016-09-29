package com.android.akshitgupta.capstoneproject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import com.android.akshitgupta.capstoneproject.task.GeoPlacesAutoCompleteTask;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

public class GeoPlacesAutoCompleteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String LOG_TAG = "PlacesActivity";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";

    //------------ make your specific key ------------
    private static final String API_KEY = "AIzaSyBRDebf1HHJNQ5ixWYP03V0IoBpHnYARnk";

    public static ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;
        ArrayList<String> list = new ArrayList<String>();
        GeoPlacesAutoCompleteTask task;
        task = new GeoPlacesAutoCompleteTask();
        //  int corePoolSize = 60;
        int maximumPoolSize = 80;
        //   int keepAliveTime = 10;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
        //  Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

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
        Log.i(LOG_TAG, "resultList {}" + resultList.toString());
        return resultList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Inside onCreate method of GeoPlacesAutocompleteActivity");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AutoCompleteTextView autoCompView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        autoCompView.setAdapter(new GooglePlacesAutocompleteAdapter(this, R.layout.list_item));
        autoCompView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String str = (String) adapterView.getItemAtPosition(position);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    class GooglePlacesAutocompleteAdapter extends ArrayAdapter<String> implements Filterable {
        private ArrayList<String> resultList;

        public GooglePlacesAutocompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int index) {
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
    }

}