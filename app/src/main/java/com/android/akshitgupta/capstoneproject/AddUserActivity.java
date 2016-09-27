package com.android.akshitgupta.capstoneproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText fromDateEtxt;
    private EditText toDateEtxt;
    private AutoCompleteTextView placePicker;
    private DatePickerDialog fromDatePickerDialog;
    private TimePickerDialog toDatePickerDialog;

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
    }


    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.dob_date);
        fromDateEtxt.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.dob_time);
        toDateEtxt.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
        placePicker = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener((View.OnClickListener) this);
        toDateEtxt.setOnClickListener((View.OnClickListener) this);
        placePicker.setOnClickListener((View.OnClickListener)this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(Calendar.HOUR,i);
                newDate.set(Calendar.MINUTE,i1);

                toDateEtxt.setText(timeFormatter.format(newDate.getTime()));
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


        if (view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if (view == toDateEtxt) {
            toDatePickerDialog.show();
        }
        else if (view == placePicker)
        {
            Intent intent = new Intent(AddUserActivity.this, GeoPlacesAutoCompleteActivity.class);
            startActivity(intent);
        }
    }
}
