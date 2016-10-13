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
import com.android.akshitgupta.capstoneproject.enums.Gender;
import com.android.akshitgupta.capstoneproject.object.User;
import com.android.akshitgupta.capstoneproject.utils.ConstantUtils;

/**
 * Created by akshitgupta on 12/10/16.
 */

public class SimpleWidgetProvider extends AppWidgetProvider {

    SharedPreferences prefs;
    private User userProfile;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int count = appWidgetIds.length;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.simple_widget);

            String defaultUserId = prefs.getString("userDefaultId", ConstantUtils.DEFAULT_WIDGET_USER_ID);
            if (ConstantUtils.DEFAULT_WIDGET_USER_ID.equals(defaultUserId)) {
                disableWidgetOnInvalidUserProfile(remoteViews, context);
            } else {
                updateUserProfile(context, defaultUserId, remoteViews);
            }

            Intent intent = new Intent(context, SimpleWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_refresh, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }

    public void disableWidgetOnInvalidUserProfile(RemoteViews remoteViews, Context context) {
        remoteViews.setTextViewText(R.id.widget_msg, context.getString(R.string.widget_error_msg));
        remoteViews.setBoolean(R.id.widget_action, "setEnabled", false);

    }

    public void updateUserProfile(Context context, String defaultUserId, RemoteViews remoteViews) {


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
        Intent intent = new Intent(context, DailyPredictionActivity.class);
        intent.putExtra("userProfile", userProfile);
        remoteViews.setBoolean(R.id.widget_action, "setEnabled", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget_action, pendingIntent);
        remoteViews.setTextViewText(R.id.simple_widget_user_name, userProfile.getUserName());
        remoteViews.setTextViewText(R.id.simple_widget_user_dob, userProfile.getDobDate() + " " + userProfile.getDobTIme());
        remoteViews.setTextViewText(R.id.simple_widget_user_city, userProfile.getCityName());
        if (Gender.MALE.getCode().equals(userProfile.getUserGender())) {
            remoteViews.setImageViewResource(R.id.simple_widget_image, R.drawable.male_default);
        } else {
            remoteViews.setImageViewResource(R.id.simple_widget_image, R.drawable.female_default);
        }
    }
}

