package com.android.akshitgupta.capstoneproject.task;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.android.akshitgupta.capstoneproject.user.GeoDetails;
import com.android.akshitgupta.capstoneproject.utils.ConstantUtils;

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

public class GeoPlaceDetailsTask extends AsyncTask<String, Void, ArrayList<GeoDetails>> {
    public static String LOG_TAG = GeoPlaceDetailsTask.class.getSimpleName();

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
        baseURL.append(ConstantUtils.PLACES_API_BASE).append(ConstantUtils.PLACE_DETAILS).append(ConstantUtils.OUT_JSON);
        ArrayList<GeoDetails> data = new ArrayList<GeoDetails>();
        try {


            Uri buildUri = Uri.parse(baseURL.toString()).buildUpon().appendQueryParameter(ConstantUtils.API_PLACEID_PARAM, URLEncoder.encode(input, ConstantUtils.ENCODING_STANDARD)).appendQueryParameter(ConstantUtils.API_KEY_PARAM, ConstantUtils.AUTOCOMPLETE_API_KEY).build();
            URL url = new URL(buildUri.toString());


            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setDoInput(true);
            urlConnection.connect();
            int status = urlConnection.getResponseCode();

            Log.i(LOG_TAG, "Google Place Details API Server status :" + status);
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

            //  data = getDescriptionDataFromJson(jsonStr);

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

    @Override
    protected void onPreExecute() {
        Log.i(LOG_TAG, "Inside onPreExecute Method");

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<GeoDetails> data) {
        Log.i(LOG_TAG, "Inside onPostExecute method");
        Log.i(LOG_TAG, "data {}" + data.toString());


        super.onPostExecute(data);
    }
}
