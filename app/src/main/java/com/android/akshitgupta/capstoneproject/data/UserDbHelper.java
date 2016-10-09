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
    private static final int DATABASE_VERSION = 5;

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserEntry.COLUMN_USER_NAME + " TEXT NOT NULL, " +
                UserEntry.COLUMN_USER_GENDER + " TEXT NOT NULL, " +
                UserEntry.COLUMN_USER_DOB_DATE + " TEXT NOT NULL, " +
                UserEntry.COLUMN_USER_DOB_TIME + " TEXT NOT NULL, " +
                UserEntry.COLUMN_COORD_LAT + " TEXT NOT NULL, " +
                UserEntry.COLUMN_COORD_LONG + " TEXT NOT NULL, " +
                UserEntry.COLUMN_CITY_NAME + " TEXT NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
