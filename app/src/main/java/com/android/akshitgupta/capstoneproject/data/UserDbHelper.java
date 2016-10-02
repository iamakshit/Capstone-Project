package com.android.akshitgupta.capstoneproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.akshitgupta.capstoneproject.data.UserContract.UserEntry;

/**
 * Created by akshitgupta on 25/09/16.
 */

public class UserDbHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 2;

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        // Create a table to hold locations.  A location consists of the string supplied in the
        // location setting, the city name, and the latitude and longitude
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                UserEntry._ID + " INTEGER PRIMARY KEY, " +
                UserEntry.COLUMN_USER_NAME + "TEXT NOT NULL, " +
                UserEntry.COLUMN_USER_GENDER + "TEXT NOT NULL, " +
                UserEntry.COLUMN_USER_DOB_DATE + "TEXT NOT NULL, " +
                UserEntry.COLUMN_USER_DOB_TIME + "TEXT NOT NULL, " +
                UserEntry.COLUMN_USER_IMAGE + "TEXT NOT NULL, " +
                UserEntry.COLUMN_CITY_NAME + " TEXT NOT NULL, " +
                UserEntry.COLUMN_COORD_LAT + " REAL NOT NULL, " +
                UserEntry.COLUMN_COORD_LONG + " REAL NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
