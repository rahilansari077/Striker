package com.example.rahil.StrikerProject;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by RAHIL on 11/02/2018.
 */

public class CustomGoalAdapter extends ArrayAdapter<Goals> {


    ProgressBar contentProgressBar;
    
    public CustomGoalAdapter(@NonNull Context context, int resource, @NonNull List<Goals> objects) {
        super(context, resource, objects);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        Goals currentGoal = getItem(position);

        View currentView = convertView;

        if(currentView == null)
        {
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.goals_content , parent , false);
        }

        TextView name = (TextView) currentView.findViewById(R.id.goal_name);
        name.setText(currentGoal.getName());

        TextView time_beginning = (TextView) currentView.findViewById(R.id.begin_time);
        time_beginning.setText(currentGoal.getBeginTime());

        TextView time_ending = (TextView) currentView.findViewById(R.id.end_time);
        time_ending.setText(currentGoal.getEndtime());


        TextView date_ending = (TextView) currentView.findViewById(R.id.end_date);
        date_ending.setText(currentGoal.getEndDate());


        TextView date_beginning = (TextView) currentView.findViewById(R.id.begin_date);
        date_beginning.setText(currentGoal.getBeginDate());

        contentProgressBar = (ProgressBar)currentView.findViewById(R.id.contentProgressBar);
        workingWithProgressBar(currentGoal.getBeginDate() , currentGoal.getEndDate());


        Log.i("Custom Adapter " , "Content View Set");


        return currentView;
    }

    public void workingWithProgressBar( String st_date , String en_date)
    {

        SimpleDateFormat st = new SimpleDateFormat("dd MMMM yyyy");

        contentProgressBar.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        Log.i("Custom Goal Adapter" , "color set for pb");

        try {
            Date d1 = st.parse(st_date);
            Date d2 = st.parse(en_date);
            Date d3 = new Date();

            Log.i("Custom Goal Adapter" , " st:"+st_date +" et"+en_date);

            st = new SimpleDateFormat("DD");

            long i1 = Long.parseLong(st.format(d1));
            long i2 = Long.parseLong(st.format(d2));
            Log.i("Custom Goal Adapter" , " i1 st is"+i1);
            Log.i("Custom Goal Adapter" , " i2 st is"+ (int)(i2));

            if(i2<i1)
            {
                contentProgressBar.setMax(0);
                contentProgressBar.setProgress(0);

            }else {
                contentProgressBar.setMax((int) (i2 - i1));
                Log.i("Custom Goal Adapter", " progress max is " + (int) (i2 - i1));

                long i3 = Long.parseLong(st.format(d3));
                Log.i("Custom Goal Adapter", " i3 st is" + (int) (i3));

                Log.i("Custom Goal Adapter", " setting th progress to" + (int) (i2 - i3));
                contentProgressBar.setProgress(Math.abs((int) (i3 - i1)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
