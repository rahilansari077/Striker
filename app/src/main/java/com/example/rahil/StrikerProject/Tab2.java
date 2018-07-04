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


public class Tab2 extends Fragment {

    private OnFragmentInteractionListener mListener;

    GoalEntryOpenHelper2 gEOH2;

    View view;
    public Tab2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_tab2, container, false);
        gEOH2 = new GoalEntryOpenHelper2(getActivity());

        final ArrayList<Goals> goals;

        goals = getList();

        CustomGoalAdapter goalsAdapter = new CustomGoalAdapter(getActivity(), 0, goals );
        ListView goalsList = (ListView) view.findViewById(R.id.goals_list_view);

        goalsList.setAdapter(goalsAdapter);


        goalsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Goals currentGoal = goals.get(position);

                Log.e("Tab2" , "Creating intent");
                Intent displayingGoal = new Intent(getActivity() , DisplayGoal.class);


                Log.e("Tab1" , "Creating object of gofd");
                GoalObjectForDisplay gofd = new GoalObjectForDisplay();
                gofd.setENTER_GOAL(currentGoal.getName());
                gofd.setSPECIFICATIONS(currentGoal.getSpecs());
                gofd.setEnd_DATE(currentGoal.getEndDate());
                gofd.setEnd_TIME(currentGoal.getEndtime());
                gofd.setStart_DATE(currentGoal.getBeginDate());
                gofd.setStart_TIME(currentGoal.getBeginTime());
                gofd.set_id(currentGoal.get_id());
                gofd.setWhichTab("2");
                Log.e("Tab2" , "All items of gofd are set ");
                displayingGoal.putExtra("GoalObjectForDisplay" , gofd );

                startActivity(displayingGoal);
            }
        });

        final CardView addGoal = (CardView) view.findViewById(R.id.card_view2);
        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addNewGoal = new Intent(getActivity(), AddNewGoal.class);
                addNewGoal.putExtra("whichTab" , 2);
                startActivity(addNewGoal);

            }
        });


        return view;

    }

    private ArrayList<Goals> getList()
    {

        SQLiteDatabase db =  gEOH2.getReadableDatabase();

        String[] projection = {
                GoalContract2.GoalEntry.ENTER_GOAL,
                GoalContract2.GoalEntry.SPECIFICATIONS,
                GoalContract2.GoalEntry.End_DATE,
                GoalContract2.GoalEntry.End_TIME,
                GoalContract2.GoalEntry.Start_DATE,
                GoalContract2.GoalEntry.Start_TIME,
                GoalContract2.GoalEntry._ID
        };

        Cursor cursor = db.query(
                GoalContract2.GoalEntry.TABLE_NAME2,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        ArrayList<Goals> goals = new ArrayList<>();
        Goals g;
        while(cursor.moveToNext())
        {int index;

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



            g= new Goals(theGoal , goalSpecs , goalStartTime , goalEndTime , goalStartDate , goalEndDate , id );
            goals.add(g);

        }
        return goals;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
