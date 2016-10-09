package com.android.akshitgupta.capstoneproject.dailyprediction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.akshitgupta.capstoneproject.R;
import com.android.akshitgupta.capstoneproject.object.UserProfile;
import com.android.akshitgupta.capstoneproject.object.request.AstroRequest;
import com.android.akshitgupta.capstoneproject.object.response.DailyPredictionResponse;
import com.android.akshitgupta.capstoneproject.task.VedicDailyPredictionTask;
import com.android.akshitgupta.capstoneproject.utils.AstroUtils;

import java.util.concurrent.ExecutionException;

public class DailyPredictionActivity extends AppCompatActivity {
    public static String TAG = DailyPredictionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_prediction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        UserProfile.User userProfile = (UserProfile.User) intent.getSerializableExtra("userProfile");
        dailyPrediction(userProfile);
    }

    public void dailyPrediction(UserProfile.User user) {

        AstroRequest request = AstroUtils.getAstroRequestByUserProfile(user, AstroUtils.DAILY_PREDICTION);
        DailyPredictionResponse response = new DailyPredictionResponse();
        Log.i(TAG, "request =" + request);

        VedicDailyPredictionTask task;
        task = new VedicDailyPredictionTask();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
        else
            task.execute(request);

        try {
            response = task.get();
            Log.i(TAG, "Response =" + response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "Response =" + response);
    }

}
