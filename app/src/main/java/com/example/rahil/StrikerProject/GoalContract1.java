package com.example.rahil.StrikerProject;

import android.provider.BaseColumns;


/**
 * Created by RAHIL on 17/02/2018.
 */

public class GoalContract1 {

    private GoalContract1() {
    }

    //Inner class defines the table contents
    public static class GoalEntry implements BaseColumns {
        public static final String TABLE_NAME1 = "GoalsEntry1";
        public static final String ENTER_GOAL = "EnterGoal";
        public static final String SPECIFICATIONS = "EnterSpecs";
        public static final String End_DATE = "GoalEndDate";
        public static final String End_TIME = "GoalEndTime";
        public static final String Start_DATE = "GoalStartDate";
        public static final String Start_TIME = "GoalStartTime";
    }
}
