package com.android.akshitgupta.capstoneproject.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by akshitgupta on 25/09/16.
 */

public class UserContract {

    public static final class UserEntry implements BaseColumns {

        public static final String CONTENT_AUTHORITY = "akshit.android.com.capstoneproject";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
        public static final String PATH_USER = "user";


        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();


        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String TABLE_NAME = "user";

        public static final String COLUMN_USER_NAME = "name";
        public static final String COLUMN_USER_GENDER = "gender";
        public static final String COLUMN_USER_DOB_DATE = "dob_date";
        public static final String COLUMN_USER_DOB_TIME = "dob_time";
        public static final String COLUMN_USER_IMAGE = "user_image";
        public static final String COLUMN_CITY_NAME = "city_name";
        // In order to uniquely pinpoint the location on the map when we launch the
        // map intent, we store the latitude and longitude as returned by openweathermap.
        public static final String COLUMN_COORD_LAT = "coord_lat";
        public static final String COLUMN_COORD_LONG = "coord_long";

        public static Uri buildUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
}
