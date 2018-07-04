package com.example.rahil.StrikerProject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNewGoal extends AppCompatActivity {

    DatePickerDialog.OnDateSetListener mdateListener;
    TimePickerDialog.OnTimeSetListener mTimeListener;

    String date_assigned;
    String time_assigned;
    String enterGoal;
    EditText enteredGoal;
    EditText specs;
    String specifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_goal);


        TextView select_date = (TextView) findViewById(R.id.select_date);

        // this clock listener will be called when the date for the goal is to be selected.
        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dp = new DatePickerDialog(AddNewGoal.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert,
                        mdateListener,
                        year, month, day);
                dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dp.show();

            }
        });

        mdateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                date_assigned = dayOfMonth + "/" + (month + 1) + "/" + year;
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formatter.parse(date_assigned);
                    formatter = new SimpleDateFormat("dd MMMM yyyy");
                    date_assigned = formatter.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.i("AddNewGoal", "date assigned");
            }
        };
        TextView select_time = (TextView) findViewById(R.id.select_time);

        // this listener will be activated when the time for the goal gets selected.
        select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();

                int _hour = cal.get(Calendar.HOUR);
                int _minute = cal.get(Calendar.MINUTE);

                TimePickerDialog tp = new TimePickerDialog(AddNewGoal.this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert
                        , mTimeListener, _hour, _minute, false);
                tp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                tp.show();
            }
        });

        mTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if ( hourOfDay == 0  && minute == 0) {
                    hourOfDay = 0; minute  = 0 ;
                }

                time_assigned = hourOfDay + ":" + minute;
                Log.i("AddNewGoal", "got time_assigned");

                SimpleDateFormat formatter = new SimpleDateFormat("k:m");
                Date date = null;
                try {
                    date = formatter.parse(time_assigned);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                formatter = new SimpleDateFormat("h:mm a");
                time_assigned = formatter.format(date);
            }
        };

        Button bt = (Button) findViewById(R.id.done_button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                enteredGoal = (EditText) findViewById(R.id.enter_goal);
                enterGoal = enteredGoal.getText().toString();

                specs = (EditText) findViewById(R.id.specifications);
                specifications = specs.getText().toString();


                if( enterGoal == "" || enterGoal == null)
                {
                    enterGoal = "Not Specified";
                }
                if( specifications == "" || specifications == null)
                    specifications  = "Not Specified";

                if(date_assigned == null)
                    date_assigned  = "Not Selected";

                if(time_assigned == null)
                    time_assigned = "Not Selected";



                specifications = specifications.trim();
                Log.e("AddNewGoal", "goal is " + specifications);
                Bundle extras = getIntent().getExtras();
                int i = (Integer) (extras.get("whichTab"));

                if (i == 1) {

                    Log.e("AddNewGoal", "whichTab is 1");


                    GoalEntryOpenHelper1 gEOH = new GoalEntryOpenHelper1(AddNewGoal.this);
                    SQLiteDatabase db = gEOH.getWritableDatabase();

                    SimpleDateFormat st = new SimpleDateFormat("dd MMMM yyyy");
                    Date now = new Date();
                    String startDate = st.format(now);

                    st = new SimpleDateFormat("h:mm a");
                    String startTime = st.format(now);


                // Create a new map of values, where column names are the keys
                    ContentValues values = new ContentValues();
                    values.put(GoalContract1.GoalEntry.ENTER_GOAL, enterGoal);
                    values.put(GoalContract1.GoalEntry.SPECIFICATIONS, specifications);
                    values.put(GoalContract1.GoalEntry.End_DATE, date_assigned);
                    values.put(GoalContract1.GoalEntry.End_TIME, time_assigned);
                    values.put(GoalContract1.GoalEntry.Start_DATE, startDate);
                    values.put(GoalContract1.GoalEntry.Start_TIME, startTime);


                // Insert the new row, returning the primary key value of the new row
                    long newRowId = db.insert(GoalContract1.GoalEntry.TABLE_NAME1, null, values);

                    if (newRowId == -1) {
                        Toast.makeText(getApplicationContext(), "Couldn't insert data, check your values", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Goal Begins!!!", Toast.LENGTH_SHORT).show();

                        Log.i("AddNewGoal", "Have put all values ");

                    }
                    Intent toTab1 = new Intent(AddNewGoal.this, MainActivity.class);
                    startActivity(toTab1);

                } else {
                    Log.e("AddNewGoal", "whichTab is 2");

                    GoalEntryOpenHelper2 gEOH2 = new GoalEntryOpenHelper2(AddNewGoal.this);
                    SQLiteDatabase db = gEOH2.getWritableDatabase();

                    SimpleDateFormat st = new SimpleDateFormat("dd MMMM yyyy");
                    Date now = new Date();
                    String startDate = st.format(now);

                    st = new SimpleDateFormat("h:mm a");
                    String startTime = st.format(now);
                // Create a new map of values, where column names are the keys
                    ContentValues values = new ContentValues();
                    values.put(GoalContract2.GoalEntry.ENTER_GOAL, enterGoal);
                    values.put(GoalContract2.GoalEntry.SPECIFICATIONS, specifications);
                    values.put(GoalContract2.GoalEntry.End_DATE, date_assigned);
                    values.put(GoalContract2.GoalEntry.End_TIME, time_assigned);
                    values.put(GoalContract2.GoalEntry.Start_DATE, startDate);
                    values.put(GoalContract2.GoalEntry.Start_TIME, startTime);


                // Insert the new row, returning the primary key value of the new row
                    long newRowId = db.insert(GoalContract2.GoalEntry.TABLE_NAME2, null, values);

                    if (newRowId == -1) {
                        Toast.makeText(getApplicationContext(), "Couldn't insert data, check your values", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Goal Begins!!!", Toast.LENGTH_SHORT).show();

                        Log.i("AddNewGoal", "Have put all values ");

                    }
                    Intent toTab2 = new Intent(AddNewGoal.this, MainActivity.class);
                    startActivity(toTab2);
                }
            }
        });
    }
}
