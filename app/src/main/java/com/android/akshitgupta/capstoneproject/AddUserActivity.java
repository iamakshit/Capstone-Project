package com.android.akshitgupta.capstoneproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG_TAG = AddUserActivity.class.getSimpleName();

    private EditText dobDate;
    private EditText dobTime;
    private EditText name;
    private AutoCompleteTextView placePicker;
    private DatePickerDialog fromDatePickerDialog;
    private TimePickerDialog toDatePickerDialog;
    private Button saveButton;
    private RadioButton maleOption;
    private RadioButton femaleOption;


    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        timeFormatter= new SimpleDateFormat("HH:mm",Locale.US);

        findViewsById();
        setDateTimeField();
        setOnClickListeners();
    }


    private void findViewsById() {
        dobDate = (EditText) findViewById(R.id.dob_date);
        dobDate.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
        dobDate.requestFocus();

        dobTime = (EditText) findViewById(R.id.dob_time);
        dobTime.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);

        placePicker = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        name = (EditText) findViewById(R.id.name);
        saveButton = (Button) findViewById(R.id.saveButton);
        maleOption = (RadioButton) findViewById(R.id.male);
        femaleOption = (RadioButton) findViewById(R.id.female);
    }

    private void setOnClickListeners()
    {
        placePicker.setOnClickListener((View.OnClickListener)this);
        dobDate.setOnClickListener((View.OnClickListener) this);
        dobTime.setOnClickListener((View.OnClickListener) this);
        saveButton.setOnClickListener((View.OnClickListener) this);
        maleOption.setOnClickListener((View.OnClickListener) this);
        femaleOption.setOnClickListener((View.OnClickListener) this);
        name.setOnClickListener((View.OnClickListener) this);

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
                newDate.set(Calendar.HOUR,i);
                newDate.set(Calendar.MINUTE,i1);

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

    @Override
    public void onClick(View view) {

        if (view == dobDate) {
            fromDatePickerDialog.show();
        } else if (view == dobTime) {
            toDatePickerDialog.show();
        }
        else if (view == placePicker)
        {
            Intent intent = new Intent(AddUserActivity.this, GeoPlacesAutoCompleteActivity.class);
            startActivity(intent);
        }
        else if (view == maleOption)
        {
            boolean maleOption = ((RadioButton) view).isChecked();
            Log.i(LOG_TAG,"Option choosen Male = "+maleOption);

        }
        else if(view ==femaleOption)
        {
            boolean femaleOption = ((RadioButton) view).isChecked();
            Log.i(LOG_TAG,"Option choosen Female = "+femaleOption);
        }
        else if(view == saveButton)
        {
            String nameText = name.getText().toString();
            Log.i(LOG_TAG,"NameText = "+nameText);
            String placePickerText = placePicker.getText().toString();
            Log.i(LOG_TAG,"PlacePicker = "+placePickerText);

            String dobDateText = dobDate.getText().toString();
            Log.i(LOG_TAG,"dobDate = "+dobDateText);

            String dobTimeText = dobTime.getText().toString();
            Log.i(LOG_TAG,"dobTime = "+dobTimeText);

            Log.i(LOG_TAG,"Save Button");
        }

    }
}
