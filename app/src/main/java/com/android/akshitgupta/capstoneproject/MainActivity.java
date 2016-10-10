package com.android.akshitgupta.capstoneproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.akshitgupta.capstoneproject.dailyprediction.DailyPredictionActivity;
import com.android.akshitgupta.capstoneproject.data.UserContract;
import com.android.akshitgupta.capstoneproject.enums.Gender;
import com.android.akshitgupta.capstoneproject.enums.Numerology;
import com.android.akshitgupta.capstoneproject.numerology.NumerologyDescriptionActivity;
import com.android.akshitgupta.capstoneproject.numerology.table.NumeroTableActivity;
import com.android.akshitgupta.capstoneproject.object.User;
import com.squareup.picasso.Picasso;

import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, UserProfileFragment.OnListFragmentInteractionListener {
    public static String TAG = MainActivity.class.getSimpleName();

    public User userProfile;
    public TourGuide mTourGuideHandler;
    SharedPreferences prefs = null;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  mTourGuideHandler.cleanUp();
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView userNameDisplayView = (TextView) header.findViewById(R.id.userName_display);
        TextView birthDetailsView = (TextView) header.findViewById(R.id.birthDetails_display);
        TextView cityNameView = (TextView) header.findViewById(R.id.nav_cityName);

        updateUserProfile();

        if (userProfile != null) {
            userNameDisplayView.setText(userProfile.getUserName());
            cityNameView.setText(userProfile.getCityName());
            ImageView genderProfileView = (ImageView) header.findViewById(R.id.nav_user_image);
            birthDetailsView.setText(userProfile.getDobDate() + " " + userProfile.getDobTIme());

            if (Gender.MALE.getCode().equals(userProfile.getUserGender())) {
                Picasso.with(getApplicationContext()).load(R.drawable.male_default).into(genderProfileView);
            } else {

                Picasso.with(getApplicationContext()).load(R.drawable.female_default).into(genderProfileView);
            }
           // Log.i(TAG, "UserProfile =" + userProfile);
        } else {
            noUserPresentToast();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about_us) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        updateUserProfile();
        if (userProfile == null) {
            noUserPresentToast();
        } else {
            // Handle navigation view item clicks here.
            int id = item.getItemId();


            if (id == R.id.daily_pred) {
                Intent intent = new Intent(MainActivity.this, DailyPredictionActivity.class);
                intent.putExtra("userProfile", userProfile);
                startActivity(intent);

            } else if (id == R.id.num_report) {
                Intent intent = new Intent(MainActivity.this, NumeroTableActivity.class);
                intent.putExtra("userProfile", userProfile);
                intent.putExtra("astroURL", Numerology.GENERAL_STATS.getCode());
                startActivity(intent);


            } else if (id == R.id.fav_lord) {

                Intent intent = new Intent(MainActivity.this, NumerologyDescriptionActivity.class);
                intent.putExtra("userProfile", userProfile);
                intent.putExtra("astroURL", Numerology.FAV_LORD.getCode());
                startActivity(intent);

            } else if (id == R.id.fav_vastu) {

                Intent intent = new Intent(MainActivity.this, NumerologyDescriptionActivity.class);
                intent.putExtra("userProfile", userProfile);
                intent.putExtra("astroURL", Numerology.FAV_VASTU.getCode());
                startActivity(intent);

            } else if (id == R.id.fav_mantra) {

                Intent intent = new Intent(MainActivity.this, NumerologyDescriptionActivity.class);
                intent.putExtra("userProfile", userProfile);
                intent.putExtra("astroURL", Numerology.FAV_MANTRA.getCode());
                startActivity(intent);

            } else if (id == R.id.fav_time) {

                Intent intent = new Intent(MainActivity.this, NumerologyDescriptionActivity.class);
                intent.putExtra("userProfile", userProfile);
                intent.putExtra("astroURL", Numerology.FAV_TIME.getCode());
                startActivity(intent);
            } else if (id == R.id.num_details) {

                Intent intent = new Intent(MainActivity.this, NumerologyDescriptionActivity.class);
                intent.putExtra("userProfile", userProfile);
                intent.putExtra("astroURL", Numerology.NUMBER_DETAILS.getCode());
                startActivity(intent);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(User item) {
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    public void updateUserProfile() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String defaultUserId = prefs.getString("userDefaultId", "1");

        Cursor userCursor = getApplicationContext().getContentResolver().query(
                UserContract.UserEntry.CONTENT_URI,
                new String[]{UserContract.UserEntry._ID, UserContract.UserEntry.COLUMN_USER_NAME, UserContract.UserEntry.COLUMN_USER_GENDER,
                        UserContract.UserEntry.COLUMN_USER_DOB_DATE, UserContract.UserEntry.COLUMN_USER_DOB_TIME, UserContract.UserEntry.COLUMN_CITY_NAME,
                        UserContract.UserEntry.COLUMN_COORD_LAT, UserContract.UserEntry.COLUMN_COORD_LONG}, UserContract.UserEntry._ID + "=?", new String[]{defaultUserId.toString()}, null);

        if (userCursor.moveToFirst()) {
            String userName = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_NAME));
            String userGender = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_GENDER));
            String userDobDate = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_DOB_DATE));
            String userDobTime = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_DOB_TIME));
            String userCity = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_CITY_NAME));
            Integer id = userCursor.getInt(userCursor.getColumnIndex(UserContract.UserEntry._ID));
            String coordLat = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_COORD_LAT));
            String coordLong = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_COORD_LONG));
            this.userProfile = new User(id, userName, userGender, userDobDate, userDobTime, null, userCity, coordLat, coordLong);

        }
        userCursor.close();
    }

    public void noUserPresentToast() {
        Toast.makeText(getApplicationContext(), getString(R.string.no_user_present), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (prefs.getBoolean("firstrun", true)) {

            Animation animation = new TranslateAnimation(0f, 0f, 200f, 0f);
            animation.setDuration(1000);
            animation.setFillAfter(true);
            animation.setInterpolator(new BounceInterpolator());


            ToolTip toolTip = new ToolTip()
                    .setTitle(getString(R.string.intro_button_title))
                    .setDescription(getString(R.string.intro_button_desc))
                    .setTextColor((getColor(R.color.introButtonTextColor)))
                    .setBackgroundColor(getColor(R.color.colorPrimaryDark))
                    .setShadow(true)
                    .setGravity(Gravity.TOP | Gravity.LEFT)
                    .setEnterAnimation(animation);
            mTourGuideHandler = TourGuide.init(this).with(TourGuide.Technique.Click)
                    .setPointer(new Pointer())
                    .setToolTip(toolTip)
                    .setOverlay(new Overlay())
                    .playOn(fab);

            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }
}

