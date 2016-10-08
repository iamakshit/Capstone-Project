package com.android.akshitgupta.capstoneproject.task;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.android.akshitgupta.capstoneproject.object.request.AstroRequest;
import com.android.akshitgupta.capstoneproject.object.response.NumeroBasicDetailsResponse;
import com.android.akshitgupta.capstoneproject.object.response.NumeroResponse;
import com.android.akshitgupta.capstoneproject.utils.AstroUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.android.akshitgupta.capstoneproject.utils.Numerology.GENERAL_STATS;

/**
 * Created by akshitgupta on 07/10/16.
 */

public class VedicNumeroTask extends AsyncTask<AstroRequest, Void, NumeroResponse> {

    public static String TAG = VedicDailyPredictionTask.class.getSimpleName();

    @Override
    protected NumeroResponse doInBackground(AstroRequest... astroRequests) {


        BufferedReader reader = null;
        String jsonStr = null;
        Log.i(TAG, "Starting ...");
        HttpURLConnection myURLConnection = null;
        NumeroResponse response = null;
        if (astroRequests == null) {
            return null;
        }
        try {

            AstroRequest astroRequest = astroRequests[0];
            String formData = AstroUtils.prepareAstroRequestString(astroRequests[0]);
            byte[] postData = formData.getBytes(StandardCharsets.UTF_8);
            StringBuilder baseURL = new StringBuilder().append(AstroUtils.BASE_URL).append(astroRequest.getAstroURL());
            Uri buildUri = Uri.parse(baseURL.toString());
            URL url = new URL(buildUri.toString());
            myURLConnection = (HttpURLConnection) url.openConnection();

            myURLConnection.setRequestProperty("Authorization", AstroUtils.basicAuth);
            myURLConnection.setRequestMethod("POST");
            myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            myURLConnection.setRequestProperty("Content-Language", "en-US");
            myURLConnection.setUseCaches(false);
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(true);
            myURLConnection.setRequestProperty("charset", "utf-8");
            myURLConnection.setRequestProperty("Content-Length", Integer.toString(formData.length()));
            myURLConnection.setReadTimeout(10000);
            myURLConnection.setConnectTimeout(10000);
            myURLConnection.setUseCaches(false);
            try (DataOutputStream wr = new DataOutputStream(myURLConnection.getOutputStream())) {
                wr.write(postData);
            }

            myURLConnection.connect();
            int status = myURLConnection.getResponseCode();
            Log.i(TAG, "Astro API Server status :" + status);
            Log.i(TAG, "Astro API Server response :" + myURLConnection.getResponseMessage());

            BufferedReader br = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            br.close();

            if (buffer.length() == 0) {
                Log.i(TAG, "bufferLength is zero");
                return null;
            }

            jsonStr = buffer.toString();
            Log.i(TAG, "JsonStr = " + jsonStr);
            if (GENERAL_STATS.getCode().equals(astroRequest.getAstroURL())) {
                NumeroBasicDetailsResponse numeroBasicDetailsResponse = new NumeroBasicDetailsResponse();
                return numeroBasicDetailsResponse;
            }
            response = AstroUtils.getNumeroResponse(jsonStr);

        } catch (IOException e) {
            Log.e(TAG, "IOException", e);

        } finally {

            if (myURLConnection != null) {
                myURLConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);

                }
            }
        }
        return response;
    }

}
