package com.android.akshitgupta.capstoneproject.dailyprediction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.akshitgupta.capstoneproject.R;
import com.android.akshitgupta.capstoneproject.object.NumeroPrediction;
import com.android.akshitgupta.capstoneproject.object.UserProfile;
import com.android.akshitgupta.capstoneproject.object.request.AstroRequest;
import com.android.akshitgupta.capstoneproject.object.response.DailyPredictionResponse;
import com.android.akshitgupta.capstoneproject.task.VedicDailyPredictionTask;
import com.android.akshitgupta.capstoneproject.utils.AstroUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DailyPredictionActivity extends AppCompatActivity {
    public static String TAG = DailyPredictionActivity.class.getSimpleName();

    List<NumeroPrediction> numeroPredictionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        UserProfile.User userProfile = (UserProfile.User) intent.getSerializableExtra("userProfile");
        dailyPrediction(userProfile);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_prediction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void dailyPrediction(UserProfile.User user) {
        numeroPredictionList = new ArrayList<>();
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

       // Log.i(TAG, "Response =" + response);

        NumeroPrediction health = new NumeroPrediction();
        health.setDescription(response.getHealth());
        health.setTitle("HEALTH");
        this.numeroPredictionList.add(health);
        NumeroPrediction emotions = new NumeroPrediction();
        emotions.setDescription(response.getEmotions());
        emotions.setTitle("EMOTION");
        this.numeroPredictionList.add(emotions);
        NumeroPrediction profession = new NumeroPrediction();
        profession.setDescription(response.getProfession());
        profession.setTitle("PROFESSION");
        this.numeroPredictionList.add(profession);
        NumeroPrediction luck = new NumeroPrediction();
        luck.setDescription(response.getLuck());
        luck.setTitle("LUCK");
        this.numeroPredictionList.add(luck);
        NumeroPrediction travel = new NumeroPrediction();
        travel.setDescription(response.getTravel());
        travel.setTitle("TRAVEL");
        this.numeroPredictionList.add(travel);
        NumeroPrediction personalLife = new NumeroPrediction();
        personalLife.setDescription(response.getPersonalLife());
        personalLife.setTitle("PERSONAL LIFE");
        this.numeroPredictionList.add(personalLife);

    }

    public List<NumeroPrediction> getNumeroPredictionList() {
        return this.numeroPredictionList;
    }
}
