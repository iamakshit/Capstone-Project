package com.android.akshitgupta.capstoneproject.numerology.table;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.akshitgupta.capstoneproject.R;
import com.android.akshitgupta.capstoneproject.object.NumeroPrediction;
import com.android.akshitgupta.capstoneproject.object.User;
import com.android.akshitgupta.capstoneproject.object.request.AstroRequest;
import com.android.akshitgupta.capstoneproject.object.response.NumeroBasicDetailsResponse;
import com.android.akshitgupta.capstoneproject.task.VedicNumeroTask;
import com.android.akshitgupta.capstoneproject.utils.AstroUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NumeroTableActivity extends AppCompatActivity {
    public static String TAG = NumeroTableActivity.class.getSimpleName();
    private List<NumeroPrediction> numeroPredictionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        User userProfile = (User) intent.getSerializableExtra("userProfile");
        String numerologyCode = intent.getStringExtra("astroURL");
        numerology(userProfile, numerologyCode);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numero_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void numerology(User user, String numerologyCode) {

        AstroRequest request = AstroUtils.getAstroRequestByUserProfile(user, numerologyCode);
        NumeroBasicDetailsResponse response = new NumeroBasicDetailsResponse();

        VedicNumeroTask task;
        task = new VedicNumeroTask();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
        else
            task.execute(request);

        try {
            response = (NumeroBasicDetailsResponse) task.get();
          //  Log.i(TAG, "Response =" + response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        this.numeroPredictionList = AstroUtils.prepareNumeroPredictionFromBasicDetailsResponse(response);
    }

    public List<NumeroPrediction> getNumeroPredictionList() {
        return numeroPredictionList;
    }
}
