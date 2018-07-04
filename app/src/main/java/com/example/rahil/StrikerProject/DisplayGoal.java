package com.example.rahil.StrikerProject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DisplayGoal extends AppCompatActivity {


    ProgressBar progressBar;
    TextView beginDate;
    TextView endDate;
    TextView beginTime;
    TextView endTime;
    TextView theGoal;
    TextView theSpecs;
    Button confirm;
    ImageView img;

    Button changeDate;
    Button changeTime;
    Button done_button;

    SQLiteDatabase db;
    GoalEntryOpenHelper1 displayGEOH1;
    GoalEntryOpenHelper2 displayGEOH2;


    DatePickerDialog.OnDateSetListener mdateListener;
    TimePickerDialog.OnTimeSetListener mTimeListener;
    String date_assigned;
    String time_assigned;


    String ENTER_GOAL;
    String SPECIFICATIONS;
    String End_DATE;
    String End_TIME;
    String Start_DATE;
    String Start_TIME;
    String _id;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_goal);

        Log.e("DisplayGoal", "getting gofd object");

        // fetching data from GoalObjectForDisplay intent
        GoalObjectForDisplay gofd = getIntent().getParcelableExtra("GoalObjectForDisplay");

        // getting all the data from goal object
        ENTER_GOAL = gofd.getENTER_GOAL();
        SPECIFICATIONS = gofd.getSPECIFICATIONS();
        End_DATE = gofd.getEnd_DATE();
        End_TIME = gofd.getEnd_TIME();
        Start_DATE = gofd.getStart_DATE();
        Start_TIME = gofd.getStart_TIME();
        _id = gofd.get_id();
        i = Integer.parseInt(gofd.getWhichTab()); // indicates which tab called this activity . important to identify the database table

        Log.e("DisplayGoal", "values fetched from gofd");
        Log.e("DisplayGoal", "which tab value is " + i);

        workingWithProgressBar();  // it populates the timeline progressbar

        if (i == 1) {
            // if i == 1 means tab1 has called and its database table is to be manipulated
            displayGEOH1 = new GoalEntryOpenHelper1(this);
            db = displayGEOH1.getWritableDatabase();
        } else {
            // if i == 2 means tab1 has called and its database table is to be manipulated

            displayGEOH2 = new GoalEntryOpenHelper2(this);
            db = displayGEOH2.getWritableDatabase();
        }

        // set value in progress bar
        // set hints in goal name and specs
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        beginDate = (TextView) findViewById(R.id.goal_begin_date);
        beginDate.setText(Start_DATE);

        endDate = (TextView) findViewById(R.id.goal_end_date);
        endDate.setText(End_DATE);

        beginTime = (TextView) findViewById(R.id.goal_begin_time);
        beginTime.setText(Start_TIME);

        endTime = (TextView) findViewById(R.id.goal_end_time);
        endTime.setText(End_TIME);

        theGoal = (TextView) findViewById(R.id.edit_goal);
        theGoal.setHint(ENTER_GOAL);

        theSpecs = (TextView) findViewById(R.id.edit_specs);
        theSpecs.setHint(SPECIFICATIONS);

        img = (ImageView) findViewById(R.id.takenImage);

        confirm = (Button) findViewById(R.id.confirm);

        changeDate = (Button) findViewById(R.id.change_date);
        changeTime = (Button) findViewById(R.id.change_time);
        confirm = (Button) findViewById(R.id.confirm);


        // fetching the  date changed by user into date_assigned variable
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dp = new DatePickerDialog(DisplayGoal.this,
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
                Log.i("AddNewGoal", "got date_assigned");
            }
        };


        // fetching the time changed by user into time_assigned variable
        changeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();

                int _hour = cal.get(Calendar.HOUR);
                int _minute = cal.get(Calendar.MINUTE);

                TimePickerDialog tp = new TimePickerDialog(DisplayGoal.this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert
                        , mTimeListener, _hour, _minute, false);
                tp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                tp.show();
            }
        });

        mTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time_assigned = hourOfDay + ":" + minute;
                Log.i("AddNewGoal", " got time_assigned");

            }
        };

        // when all values are set  the corresponding  database row will be updated
        done_button = (Button) findViewById(R.id.done_edit_button);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("DisplayGoal", "inside on click");

                if( date_assigned == null)
                    date_assigned=End_DATE;


                if( time_assigned == null)
                    time_assigned =End_TIME;

                String main_goal = theGoal.getText().toString();
                if(main_goal == "" || main_goal == null ) {
                    main_goal = ENTER_GOAL;
                }
                String main_specs = theSpecs.getText().toString();
                if(main_specs == "" || main_specs == null)
                    main_specs = SPECIFICATIONS;

                ContentValues values = new ContentValues();

                if (i == 1) {
                    Log.e("DisplayGoal", "inside content for Tab1");
                    values.put(GoalContract1.GoalEntry.ENTER_GOAL, main_goal);
                    values.put(GoalContract1.GoalEntry.SPECIFICATIONS, main_specs);
                    values.put(GoalContract1.GoalEntry.End_DATE, date_assigned);
                    values.put(GoalContract1.GoalEntry.End_TIME, time_assigned);
                    values.put(GoalContract1.GoalEntry.Start_DATE, Start_DATE);
                    values.put(GoalContract1.GoalEntry.Start_TIME, Start_TIME);

                    Log.e("DisplayGoal", "now gonna update");

                    db.update(GoalContract1.GoalEntry.TABLE_NAME1, values, GoalContract2.GoalEntry._ID + " =" + _id, null);

                    Intent toTab1 = new Intent(DisplayGoal.this, MainActivity.class);
                    startActivity(toTab1);



                } else {
                    Log.e("DisplayGoal", "inside content for Tabs2");

                    values.put(GoalContract2.GoalEntry.ENTER_GOAL, theGoal.getText().toString());
                    values.put(GoalContract2.GoalEntry.SPECIFICATIONS, theSpecs.getText().toString());
                    values.put(GoalContract2.GoalEntry.End_DATE, date_assigned);
                    values.put(GoalContract2.GoalEntry.End_TIME, time_assigned);
                    values.put(GoalContract2.GoalEntry.Start_DATE, Start_DATE);
                    values.put(GoalContract2.GoalEntry.Start_TIME, Start_TIME);
                    db.update(GoalContract2.GoalEntry.TABLE_NAME2, values, GoalContract2.GoalEntry._ID + " =" + _id, null);
                    Intent toTab2 = new Intent(DisplayGoal.this, MainActivity.class);
                    startActivity(toTab2);

                }

            }
        });

    }


    public void imageIntent(View v) {


        AlertDialog.Builder builder = new AlertDialog.Builder(DisplayGoal.this);

        builder.setMessage("Achieved the Goal?").setCancelable(true).setPositiveButton("Yes" ,new  DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (i == 1) {
                    // if i == 1 means tab1 has called and its database table is to be manipulated
                    displayGEOH1 = new GoalEntryOpenHelper1(DisplayGoal.this);
                    db = displayGEOH1.getWritableDatabase();

                    db.delete(GoalContract1.GoalEntry.TABLE_NAME1 , "_id = "+_id , null   );
                    Log.i("Display goal " , "Row deleted id"+ _id);
                } else {
                    // if i == 2 means tab1 has called and its database table is to be manipulated

                    displayGEOH2 = new GoalEntryOpenHelper2(DisplayGoal.this);
                    db = displayGEOH2.getWritableDatabase();

                    db.delete(GoalContract2.GoalEntry.TABLE_NAME2 , "_id = "+_id , null   );
                    Log.i("Display goal " , "Row deleted id"+ _id);
                }
                Toast.makeText(getApplicationContext(), "Great Work, Keep Going!!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DisplayGoal.this , MainActivity.class);
                startActivity(i);
            }
        } ).setNegativeButton("Nope, Working On It!", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert  = builder.create();
        alert.setTitle("Accomplishment");
        alert.show();

    }

    public void workingWithProgressBar() {
        SimpleDateFormat st = new SimpleDateFormat("dd MMMM yyyy");

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        try {
            Date d1 = st.parse(Start_DATE);
            Date d2 = st.parse(End_DATE);
            Date d3 = new Date();

            Log.i("Display GoalProgress", " st:" + Start_DATE + " et" + End_DATE);

            st = new SimpleDateFormat("DD");

            long i1 = Long.parseLong(st.format(d1));
            long i2 = Long.parseLong(st.format(d2));
            Log.i("Display GoalProgress", " i1 st is" + i1);
            Log.i("Display GoalProgress", " i2 st is" + (int) (i2));
            if(i2<i1)
            {
                progressBar.setMax(0);
                progressBar.setProgress(0);
                Log.e("Custom Goal Adapter", "set value 0 for progress bar");

            }else {
                progressBar.setMax((int) (i2 - i1));
                Log.i("Custom Goal Adapter", " progress max is " + (int) (i2 - i1));

                long i3 = Long.parseLong(st.format(d3));
                Log.i("Custom Goal Adapter", " i3 st is" + (int) (i3));

                Log.i("Custom Goal Adapter", " setting th progress to" + (int) (i2 - i3));
                progressBar.setProgress(Math.abs((int) (i3 - i1)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

