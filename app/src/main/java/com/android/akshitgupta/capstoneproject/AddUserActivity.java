package com.android.akshitgupta.capstoneproject;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.akshitgupta.capstoneproject.data.UserContract;
import com.android.akshitgupta.capstoneproject.enums.Gender;
import com.android.akshitgupta.capstoneproject.object.GeoDetails;
import com.android.akshitgupta.capstoneproject.object.GeoPlaceDetails;
import com.android.akshitgupta.capstoneproject.object.User;
import com.android.akshitgupta.capstoneproject.task.GeoPlaceDetailsTask;
import com.android.akshitgupta.capstoneproject.view.GooglePlacesAutocompleteAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static com.android.akshitgupta.capstoneproject.utils.ConstantUtils.PERMISSIONS_REQUEST_WRITE_CONTACTS;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG_TAG = AddUserActivity.class.getSimpleName();
    String placeId;
    private EditText dobDate;
    private EditText dobTime;
    private EditText name;
    private DatePickerDialog fromDatePickerDialog;
    private TimePickerDialog toDatePickerDialog;
    private Button saveButton;
    private RadioButton maleOption;
    private RadioButton femaleOption;
    private AutoCompleteTextView autoCompView;
    private GeoDetails geoDetails;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;
    private Integer id;

    public static GeoPlaceDetails getLocationDetails(String input) {
        GeoPlaceDetails geoPlaceDetails = null;
        GeoPlaceDetailsTask task;
        task = new GeoPlaceDetailsTask();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, input);
        else
            task.execute(input);

        try {
            geoPlaceDetails = task.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return geoPlaceDetails;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        timeFormatter = new SimpleDateFormat("HH:mm", Locale.US);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("userProfile");
        findViewsById(user);
        setDateTimeField();
        setOnClickListeners();
    }

    private void findViewsById(User user) {

        dobDate = (EditText) findViewById(R.id.dob_date);
        dobDate.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
        dobDate.requestFocus();

        autoCompView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        dobTime = (EditText) findViewById(R.id.dob_time);
        dobTime.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
        name = (EditText) findViewById(R.id.name);
        saveButton = (Button) findViewById(R.id.saveButton);
        maleOption = (RadioButton) findViewById(R.id.male);
        femaleOption = (RadioButton) findViewById(R.id.female);
        if (user != null) {
            this.id = user.getId();
            name.setText(user.getUserName());
            dobDate.setText(user.getDobDate());
            dobTime.setText(user.getDobTIme());

            if (user.getUserGender().equals(Gender.MALE.getCode())) {
                maleOption.setChecked(true);
                femaleOption.setChecked(false);

            } else {
                femaleOption.setChecked(true);
                femaleOption.setChecked(false);

            }
        }
    }

    private void setOnClickListeners() {
        dobDate.setOnClickListener((View.OnClickListener) this);
        dobTime.setOnClickListener((View.OnClickListener) this);
        saveButton.setOnClickListener((View.OnClickListener) this);
        maleOption.setOnClickListener((View.OnClickListener) this);
        femaleOption.setOnClickListener((View.OnClickListener) this);
        name.setOnClickListener((View.OnClickListener) this);

        autoCompView.setAdapter(new GooglePlacesAutocompleteAdapter(this, R.layout.list_item));
        autoCompView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                geoDetails = (GeoDetails) autoCompView.getAdapter().getItem(position);
                placeId = geoDetails.getPlaceId();
                Toast.makeText(context, "You selected :: " + geoDetails.getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dobDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(Calendar.HOUR, i);
                newDate.set(Calendar.MINUTE, i1);

                dobTime.setText(timeFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean errorAlerts() {
        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError("Name is mandatory");
            return false;
        }

        if (TextUtils.isEmpty(dobDate.getText().toString())) {
            dobDate.setError("Date of birth is mandatory");
            return false;
        }

        if (TextUtils.isEmpty(dobTime.getText().toString())) {
            dobTime.setError("Time of birth is mandatory");
            return false;
        }

        if (TextUtils.isEmpty(maleOption.getText().toString()) && TextUtils.isEmpty(femaleOption.getText().toString())) {
            maleOption.setError("Gender options has to be selected");
            return false;
        }

        if (TextUtils.isEmpty(autoCompView.getText().toString())) {
            autoCompView.setError("Place of birth is mandatory");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {

        if (view == dobDate) {
            fromDatePickerDialog.show();
        } else if (view == dobTime) {
            toDatePickerDialog.show();
        } else if (view == saveButton) {

            boolean isAlertFound = errorAlerts();
            if (!isAlertFound) {
                return;
            }

            String nameText = name.getText().toString();
            String dobDateText = dobDate.getText().toString();
            String dobTimeText = dobTime.getText().toString();
            String location = autoCompView.getText().toString();
            boolean isMale = maleOption.isChecked();
            String gender = Gender.MALE.getCode();
            if (!isMale) {
                gender = Gender.FEMALE.getCode();
            }

            if (placeId == null) {
                autoCompView.setError("Enter a valid place");
                return;
            }
            GeoPlaceDetails geoPlaceDetails = getLocationDetails(placeId);


            User user = new User();
            user.setUserName(nameText);
            user.setDobDate(dobDateText);
            user.setDobTIme(dobTimeText);
            user.setUserGender(gender);
            user.setCoordLat(geoPlaceDetails.getLatitude());
            user.setCoordLong(geoPlaceDetails.getLongitude());
            user.setCityName(location);
            Log.i(LOG_TAG, "Save Button User =" + user);

            addUser(user);
            Toast.makeText(getApplicationContext(), "Successfully added new user profile", Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    public void addUser(User user) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, PERMISSIONS_REQUEST_WRITE_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else if (id == null) {
            getApplicationContext().deleteDatabase(UserContract.UserEntry.TABLE_NAME);
            ContentValues userValues = new ContentValues();
            userValues.put(UserContract.UserEntry._ID, user.getId());
            userValues.put(UserContract.UserEntry.COLUMN_USER_NAME, user.getUserName());
            userValues.put(UserContract.UserEntry.COLUMN_USER_GENDER, user.getUserGender());
            userValues.put(UserContract.UserEntry.COLUMN_USER_DOB_DATE, user.getDobDate());
            userValues.put(UserContract.UserEntry.COLUMN_USER_DOB_TIME, user.getDobTIme());
            userValues.put(UserContract.UserEntry.COLUMN_CITY_NAME, user.getCityName());
            userValues.put(UserContract.UserEntry.COLUMN_COORD_LAT, user.getCoordLat());
            userValues.put(UserContract.UserEntry.COLUMN_COORD_LONG, user.getCoordLong());
            // Finally, insert location data into the database.
            Uri insertedUri = getApplicationContext().getContentResolver().insert(
                    UserContract.UserEntry.CONTENT_URI,
                    userValues
            );

            // The resulting URI contains the ID for the row.  Extract the locationId from the Uri.
            Long userId = ContentUris.parseId(insertedUri);
            id=userId.intValue();
        } else {
            ContentValues userValues = new ContentValues();
            userValues.put(UserContract.UserEntry._ID, id);
            userValues.put(UserContract.UserEntry.COLUMN_USER_NAME,user.getUserName());
            userValues.put(UserContract.UserEntry.COLUMN_USER_GENDER, user.getUserGender());
            userValues.put(UserContract.UserEntry.COLUMN_USER_DOB_DATE, user.getDobDate());
            userValues.put(UserContract.UserEntry.COLUMN_USER_DOB_TIME, user.getDobTIme());
            userValues.put(UserContract.UserEntry.COLUMN_CITY_NAME, user.getCityName());
            userValues.put(UserContract.UserEntry.COLUMN_COORD_LAT, user.getCoordLat());
            userValues.put(UserContract.UserEntry.COLUMN_COORD_LONG, user.getCoordLong());

           // Uri uri = ContentUris.withAppendedId(Words.CONTENT_URI, id);
            String[] args = {String.valueOf(id)};
            getApplicationContext().getContentResolver().update(UserContract.UserEntry.CONTENT_URI, userValues, UserContract.UserEntry._ID + "=?", args);
            Log.i(LOG_TAG, "Updated user with id = " + id + "  Id" + id);

        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userDefaultId", id.toString());
        editor.commit();
    }

}