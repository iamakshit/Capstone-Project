package com.android.akshitgupta.capstoneproject.numerology;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

import com.android.akshitgupta.capstoneproject.R;
import com.android.akshitgupta.capstoneproject.object.User;
import com.android.akshitgupta.capstoneproject.object.request.AstroRequest;
import com.android.akshitgupta.capstoneproject.object.response.NumeroResponse;
import com.android.akshitgupta.capstoneproject.task.VedicNumeroTask;
import com.android.akshitgupta.capstoneproject.utils.AstroUtils;

import org.apache.commons.lang3.StringUtils;

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
        User userProfile = (User) intent.getSerializableExtra("userProfile");
        String numerologyCode = intent.getStringExtra("astroURL");
        if (StringUtils.isNotEmpty(numerologyCode)) {
            numerology(userProfile, numerologyCode);
        } else {
            //Need to fill the About Us page
            TextView numerologyDesription = (TextView) findViewById(R.id.numero_desc);
            numerologyDesription.setText(Html.fromHtml(AstroUtils.getAboutUsContent()));
            toolbar = (Toolbar) findViewById(R.id.numero_toolbar);
            toolbar.setTitle(getString(R.string.about_us));
        }

    }

    public void numerology(User user, String numerologyCode) {

        AstroRequest request = AstroUtils.getAstroRequestByUserProfile(user, numerologyCode);
        NumeroResponse response = new NumeroResponse();

        VedicNumeroTask task;
        task = new VedicNumeroTask();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
        else
            task.execute(request);

        try {
            response = task.get();
            //Log.i(TAG, "Response =" + response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        TextView numerologyDesription = (TextView) findViewById(R.id.numero_desc);
        numerologyDesription.setText(Html.fromHtml(response.getDescription()));
        Toolbar toolbar = (Toolbar) findViewById(R.id.numero_toolbar);
        toolbar.setTitle(response.getTitle());
    }

}
