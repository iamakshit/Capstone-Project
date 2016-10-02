package com.android.akshitgupta.capstoneproject.task;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.android.akshitgupta.capstoneproject.object.GeoDetails;
import com.android.akshitgupta.capstoneproject.utils.ConstantUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by akshitgupta on 29/09/16.
 */

public class GeoPlacesAutoCompleteTask extends AsyncTask<String, Void, ArrayList<GeoDetails>> {
    public static final int size = 10;

    public static String LOG_TAG = GeoPlacesAutoCompleteTask.class.getSimpleName();

    @Override
    protected ArrayList<GeoDetails> doInBackground(String... params) {

        if (params == null) {
            return null;
        }

        String input = params[0];
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonStr = null;
        Log.i(LOG_TAG, "Starting ...");

        StringBuilder baseURL = new StringBuilder();
        baseURL.append(ConstantUtils.PLACES_API_BASE).append(ConstantUtils.TYPE_AUTOCOMPLETE).append(ConstantUtils.OUT_JSON);
        ArrayList<GeoDetails> data = new ArrayList<GeoDetails>();
        try {


            Uri buildUri = Uri.parse(baseURL.toString()).buildUpon().appendQueryParameter(ConstantUtils.API_LANGUAGE_PARAM, ConstantUtils.API_LANGUAGE).appendQueryParameter(ConstantUtils.API_TYPES_PARAM, ConstantUtils.API_TYPES).appendQueryParameter(ConstantUtils.API_INPUT_PARAM, URLEncoder.encode(input, ConstantUtils.ENCODING_STANDARD)).appendQueryParameter(ConstantUtils.API_KEY_PARAM, ConstantUtils.AUTOCOMPLETE_API_KEY).build();
            URL url = new URL(buildUri.toString());


            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setDoInput(true);
            urlConnection.connect();
            int status = urlConnection.getResponseCode();

            Log.i(LOG_TAG, "Google AutoComplete API Server status :" + status);
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            br.close();

            if (buffer.length() == 0) {
                Log.i(LOG_TAG, "bufferLength is zero");
                return null;
            }

            jsonStr = buffer.toString();

            data = getDescriptionDataFromJson(jsonStr);

        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException", e);

        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);

                }
            }
        }


        return data;
    }

    private ArrayList<GeoDetails> getDescriptionDataFromJson(String jsonStr) {
        ArrayList<GeoDetails> dataList = new ArrayList<GeoDetails>();
        try {

            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            dataList = new ArrayList<GeoDetails>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                GeoDetails geoDetails = new GeoDetails();
                geoDetails.setDescription(predsJsonArray.getJSONObject(i).getString("description"));
                geoDetails.setPlaceId(predsJsonArray.getJSONObject(i).getString("place_id"));
                dataList.add(geoDetails);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }
        return dataList;
    }

    @Override
    protected void onPreExecute() {
        Log.i(LOG_TAG, "Inside onPreExecute Method");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<GeoDetails> data) {
        Log.i(LOG_TAG, "Inside onPostExecute method");
        super.onPostExecute(data);
    }
}
