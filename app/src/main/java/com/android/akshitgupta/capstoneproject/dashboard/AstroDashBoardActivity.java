package com.android.akshitgupta.capstoneproject.dashboard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.android.akshitgupta.capstoneproject.R;
import com.android.akshitgupta.capstoneproject.dailyprediction.DailyPredictionActivity;
import com.android.akshitgupta.capstoneproject.enums.Numerology;
import com.android.akshitgupta.capstoneproject.numerology.NumerologyDescriptionActivity;
import com.android.akshitgupta.capstoneproject.numerology.table.NumeroTableActivity;
import com.android.akshitgupta.capstoneproject.object.User;

public class AstroDashBoardActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView dailyPredView;
    ImageView numReportView;
    ImageView favMantraView;
    ImageView lordView;
    ImageView favTimeView;
    ImageView numDetailsView;
    ImageView favVastuView;
    User userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        this.userProfile = (User) intent.getSerializableExtra("userProfile");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dailyPredView = (ImageView) findViewById(R.id.dash_daily_pred);
        numReportView = (ImageView) findViewById(R.id.dash_num_report);
        favMantraView = (ImageView) findViewById(R.id.dash_fav_mantra);
        lordView = (ImageView) findViewById(R.id.dash_lord);
        favTimeView = (ImageView) findViewById(R.id.dash_fav_time);
        numDetailsView = (ImageView) findViewById(R.id.dash_num_details);
        favVastuView = (ImageView) findViewById(R.id.dash_fav_vastu);
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        dailyPredView.setOnClickListener((View.OnClickListener) this);
        numReportView.setOnClickListener((View.OnClickListener) this);
        favMantraView.setOnClickListener((View.OnClickListener) this);
        lordView.setOnClickListener((View.OnClickListener) this);
        favTimeView.setOnClickListener((View.OnClickListener) this);
        numDetailsView.setOnClickListener((View.OnClickListener) this);
        favVastuView.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        if (view == dailyPredView) {
            view.setBackgroundColor(Color.GRAY);
            Intent intent = new Intent(AstroDashBoardActivity.this, DailyPredictionActivity.class);
            intent.putExtra("userProfile", userProfile);
            startActivity(intent);

        } else if (view == numReportView) {
            view.setBackgroundColor(Color.GRAY);
            Intent intent = new Intent(AstroDashBoardActivity.this, NumeroTableActivity.class);
            intent.putExtra("userProfile", userProfile);
            intent.putExtra("astroURL", Numerology.GENERAL_STATS.getCode());
            startActivity(intent);
        } else if (view == favMantraView) {
            view.setBackgroundColor(Color.GRAY);
            Intent intent = new Intent(AstroDashBoardActivity.this, NumerologyDescriptionActivity.class);
            intent.putExtra("userProfile", userProfile);
            intent.putExtra("astroURL", Numerology.FAV_MANTRA.getCode());
            startActivity(intent);
        } else if (view == lordView) {
            view.setBackgroundColor(Color.GRAY);
            Intent intent = new Intent(AstroDashBoardActivity.this, NumerologyDescriptionActivity.class);
            intent.putExtra("userProfile", userProfile);
            intent.putExtra("astroURL", Numerology.FAV_LORD.getCode());
            startActivity(intent);
        } else if (view == favTimeView) {
            Intent intent = new Intent(AstroDashBoardActivity.this, NumerologyDescriptionActivity.class);
            intent.putExtra("userProfile", userProfile);
            intent.putExtra("astroURL", Numerology.FAV_TIME.getCode());
            startActivity(intent);
        } else if (view == numDetailsView) {
            view.setBackgroundColor(Color.GRAY);
            Intent intent = new Intent(AstroDashBoardActivity.this, NumerologyDescriptionActivity.class);
            intent.putExtra("userProfile", userProfile);
            intent.putExtra("astroURL", Numerology.NUMBER_DETAILS.getCode());
            startActivity(intent);
        } else if (view == favVastuView) {
            view.setBackgroundColor(Color.GRAY);
            Intent intent = new Intent(AstroDashBoardActivity.this, NumerologyDescriptionActivity.class);
            intent.putExtra("userProfile", userProfile);
            intent.putExtra("astroURL", Numerology.FAV_VASTU.getCode());
            startActivity(intent);
        }

    }
}
