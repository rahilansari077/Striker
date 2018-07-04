package com.example.rahil.StrikerProject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by RAHIL on 27/02/2018.
 */

    public class GoalEntryOpenHelper1 extends SQLiteOpenHelper {

        private static  String SQL_CREATE_ENTRIES;
        private static  String SQL_DELETE_ENTRIES;

    public GoalEntryOpenHelper1(Context context) {

            super(context, GoalContract1.GoalEntry.TABLE_NAME1, null , 1);

        }
        public void onCreate(SQLiteDatabase db) {
            SQL_CREATE_ENTRIES =
                    "CREATE TABLE " + GoalContract1.GoalEntry.TABLE_NAME1 + " (" +
                            GoalContract1.GoalEntry._ID + " INTEGER PRIMARY KEY," +
                            GoalContract1.GoalEntry.ENTER_GOAL + " TEXT," +
                            GoalContract1.GoalEntry.SPECIFICATIONS + " TEXT ,"+
                            GoalContract1.GoalEntry.Start_DATE + " TEXT ," +
                            GoalContract1.GoalEntry.End_DATE + " TEXT ," +
                            GoalContract1.GoalEntry.Start_TIME + " TEXT ," +
                            GoalContract1.GoalEntry.End_TIME+ " TEXT)";
            db.execSQL(SQL_CREATE_ENTRIES);
            Log.i("OpenHelper" , "database created");
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

             SQL_DELETE_ENTRIES =
                    "DROP TABLE IF EXISTS " + GoalContract1.GoalEntry.TABLE_NAME1;
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

}

