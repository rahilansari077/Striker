package com.example.rahil.StrikerProject;

import android.provider.BaseColumns;

/**
 * Created by RAHIL on 02/03/2018.
 */

public class GoalContract2 {

    private GoalContract2() {}

    /* Inner class defines the table contents */
    public static class GoalEntry implements BaseColumns {
        public static final String TABLE_NAME2 = "GoalsEntry2";
        public static final String ENTER_GOAL = "EnterGoal";
        public static final String SPECIFICATIONS = "EnterSpecs";
        public static final String End_DATE = "GoalEndDate";
        public static final String End_TIME = "GoalEndTime";
        public static final String Start_DATE = "GoalStartDate";
        public static final String Start_TIME = "GoalStartTime";

    }
}
