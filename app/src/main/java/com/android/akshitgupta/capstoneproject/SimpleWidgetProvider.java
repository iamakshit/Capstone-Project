package com.android.akshitgupta.capstoneproject;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.android.akshitgupta.capstoneproject.dailyprediction.DailyPredictionActivity;
import com.android.akshitgupta.capstoneproject.data.UserContract;
import com.android.akshitgupta.capstoneproject.data.UserProvider;
import com.android.akshitgupta.capstoneproject.object.User;

import java.util.Random;

/**
 * Created by akshitgupta on 12/10/16.
 */

public class SimpleWidgetProvider extends AppWidgetProvider {

    private User userProfile;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int count = appWidgetIds.length;

        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];
            String number = String.format("%03d", (new Random().nextInt(900) + 100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.simple_widget);
            remoteViews.setTextViewText(R.id.textView, number);

            updateUserProfile(context);
            // Create intent to launch MainActivity
            Intent intent1 = new Intent(context, DailyPredictionActivity.class);
            intent1.putExtra("userProfile", userProfile);
            PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0, intent1, 0);
            remoteViews.setOnClickPendingIntent(R.id.actionButton, pendingIntent1);

           /* Intent intent = new Intent(context, SimpleWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.actionButton, pendingIntent);
           */


            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
    public void updateUserProfile(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String defaultUserId = prefs.getString("userDefaultId", "1");

        Cursor userCursor = context.getContentResolver().query(
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
    }

