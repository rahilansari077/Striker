package com.example.rahil.StrikerProject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Tab1 extends Fragment {

    private OnFragmentInteractionListener mListener;

    public Tab1() {
        // Required empty public constructor
    }

    GoalEntryOpenHelper1 gEOH1;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_tab1, container, false);
        Log.i("fragment container 1", "" + container.toString());

        gEOH1 = new GoalEntryOpenHelper1(getActivity());

        final ArrayList<Goals> goals = getList();
        CustomGoalAdapter goalsAdapter = new CustomGoalAdapter(getActivity(), 0, goals);

        ListView goalsList = (ListView) view.findViewById(R.id.goals_list_view);

        goalsList.setAdapter(goalsAdapter);

        goalsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Goals currentGoal = goals.get(position);

                Log.e("Tab1", "Creating intent");
                Intent displayingGoal = new Intent(getActivity(), DisplayGoal.class);


                Log.e("Tab1", "Creating object of gofd");
                GoalObjectForDisplay gofd = new GoalObjectForDisplay();
                gofd.setENTER_GOAL(currentGoal.getName());
                gofd.setSPECIFICATIONS(currentGoal.getSpecs());
                gofd.setEnd_DATE(currentGoal.getEndDate());
                gofd.setEnd_TIME(currentGoal.getEndtime());
                gofd.setStart_DATE(currentGoal.getBeginDate());
                gofd.setStart_TIME(currentGoal.getBeginTime());
                gofd.set_id(currentGoal.get_id());
                gofd.setWhichTab("1");
                Log.e("Tab1", "All items of gofd are set ");
                displayingGoal.putExtra("GoalObjectForDisplay", gofd);

                startActivity(displayingGoal);
            }
        });


        final CardView addGoal = (CardView) view.findViewById(R.id.card_view1);

        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addNewGoal = new Intent(getActivity(), AddNewGoal.class);
                addNewGoal.putExtra("whichTab", 1);
                startActivity(addNewGoal);

            }
        });

        return view;
    }


    private ArrayList<Goals> getList() {
        SQLiteDatabase db = gEOH1.getReadableDatabase();

        String[] projection = {
                GoalContract1.GoalEntry.ENTER_GOAL,
                GoalContract1.GoalEntry.SPECIFICATIONS,
                GoalContract1.GoalEntry.End_DATE,
                GoalContract1.GoalEntry.End_TIME,
                GoalContract1.GoalEntry.Start_DATE,
                GoalContract1.GoalEntry.Start_TIME,
                GoalContract1.GoalEntry._ID
        };

        Cursor cursor = db.query(
                GoalContract1.GoalEntry.TABLE_NAME1,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        ArrayList<Goals> goals = new ArrayList<>();
        Goals g;
        while (cursor.moveToNext()) {
            int index;

            index = cursor.getColumnIndex(GoalContract1.GoalEntry.ENTER_GOAL);
            String theGoal = cursor.getString(index);

            index = cursor.getColumnIndex(GoalContract1.GoalEntry.SPECIFICATIONS);
            String goalSpecs = cursor.getString(index);

            index = cursor.getColumnIndex(GoalContract1.GoalEntry.End_DATE);
            String goalEndDate = cursor.getString(index);

            index = cursor.getColumnIndex(GoalContract1.GoalEntry.End_TIME);
            String goalEndTime = cursor.getString(index);

            index = cursor.getColumnIndex(GoalContract1.GoalEntry.Start_DATE);
            String goalStartDate = cursor.getString(index);

            index = cursor.getColumnIndex(GoalContract1.GoalEntry.Start_TIME);
            String goalStartTime = cursor.getString(index);


            index = cursor.getColumnIndex(GoalContract1.GoalEntry._ID);
            String id = cursor.getString(index);
            Log.e("Tab1 fragment", "Id fetched is " + id);


            g = new Goals(theGoal, goalSpecs, goalStartTime, goalEndTime, goalStartDate, goalEndDate, id);
            goals.add(g);


        }
        return goals;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}
