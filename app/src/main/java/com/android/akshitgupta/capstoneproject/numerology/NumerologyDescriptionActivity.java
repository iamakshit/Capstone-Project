package com.android.akshitgupta.capstoneproject.numerology;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.android.akshitgupta.capstoneproject.R;
import com.android.akshitgupta.capstoneproject.object.UserProfile;
import com.android.akshitgupta.capstoneproject.object.request.AstroRequest;
import com.android.akshitgupta.capstoneproject.object.response.NumeroResponse;
import com.android.akshitgupta.capstoneproject.task.VedicNumeroTask;

import java.util.concurrent.ExecutionException;

public class NumerologyDescriptionActivity extends AppCompatActivity {
    public static String TAG = NumerologyDescriptionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerology_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        UserProfile.User userProfile = (UserProfile.User) intent.getSerializableExtra("userProfile");
        String numerologyCode = intent.getStringExtra("astroURL");
        numerology(userProfile, numerologyCode);

    }

    public void numerology(UserProfile.User user, String numerologyCode) {

        String[] date = user.getDobDate().split("-");
        String[] hour = user.getDobTIme().split(":");

        AstroRequest request = new AstroRequest();
        request.setAstroURL(numerologyCode);
        request.setName(user.getUserName());
        request.setDay(date[0]);
        request.setMonth(date[1]);
        request.setYear(date[2]);
        request.setHour(hour[0]);
        request.setMin(hour[1]);
        request.setLat(user.getCoordLat());
        request.setLon(user.getCoordLong());
        request.setTzone(5.5);
        NumeroResponse response = new NumeroResponse();
        Log.i(TAG, "request =" + request);

        VedicNumeroTask task;
        task = new VedicNumeroTask();
        int maximumPoolSize = 80;

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
        TextView numerologyDesription= (TextView) findViewById(R.id.numero_desc);
        numerologyDesription.setText(Html.fromHtml(response.getDescription()));
        Toolbar toolbar = (Toolbar)findViewById(R.id.numero_toolbar);
        toolbar.setTitle(response.getTitle());
    }


}
