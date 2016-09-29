package com.android.akshitgupta.capstoneproject.task;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.android.akshitgupta.capstoneproject.utils.ConstantUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by akshitgupta on 29/09/16.
 */

public class GeoPlacesAutoCompleteTask extends AsyncTask<String, Void, ArrayList<String>> {
    public static final String API_KEY_PARAM = "key";
    public static final String API_LANGUAGE_PARAM = "language";
    public static final String API_TYPES_PARAM = "types";
    public static final String API_INPUT_PARAM = "input";
    public static final String ENCODING_STANDARD = "utf8";
    public static final String API_LANGUAGE = "en";
    public static final String API_TYPES = "geocode";
    public static final int size = 10;
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json?";
    public static String LOG_TAG = GeoPlacesAutoCompleteTask.class.getSimpleName();

    public static ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);

            URL url = new URL(sb.toString());

            System.out.println("URL: " + url);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }


        return resultList;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {

        if (params == null) {
            return null;
        }

        String input = params[0];
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonStr = null;
        Log.i(LOG_TAG, "Starting ...");

        StringBuilder baseURL = new StringBuilder();
        baseURL.append(PLACES_API_BASE).append(TYPE_AUTOCOMPLETE).append(OUT_JSON);
        ArrayList<String> data = new ArrayList<String>();
        try {


            Uri buildUri = Uri.parse(baseURL.toString()).buildUpon().appendQueryParameter(API_LANGUAGE_PARAM, API_LANGUAGE).appendQueryParameter(API_TYPES_PARAM, API_TYPES).appendQueryParameter(API_INPUT_PARAM, URLEncoder.encode(input, ENCODING_STANDARD)).appendQueryParameter(API_KEY_PARAM, ConstantUtils.API_KEY).build();
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
//            Log.i(LOG_TAG, "Output recieved {}" + jsonStr);

            data = getDescriptionDataFromJson(jsonStr);

            // Log.i(LOG_TAG, "JsonStr = " + jsonStr);

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

    private ArrayList<String> getDescriptionDataFromJson(String jsonStr) {
        ArrayList<String> dataList = new ArrayList<String>();
        try {

            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            dataList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                //  System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                //  System.out.println("============================================================");
                dataList.add(predsJsonArray.getJSONObject(i).getString("description"));
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
    protected void onPostExecute(ArrayList<String> data) {
        Log.i(LOG_TAG, "Inside onPostExecute method");
        Log.i(LOG_TAG, "data {}" + data.toString());


        super.onPostExecute(data);
    }
}
