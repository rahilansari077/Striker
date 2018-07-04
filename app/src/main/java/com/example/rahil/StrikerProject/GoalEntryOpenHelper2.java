package com.example.rahil.StrikerProject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by RAHIL on 02/03/2018.
 */

public class GoalEntryOpenHelper2 extends SQLiteOpenHelper {

    private static  String SQL_CREATE_ENTRIES;
    private static  String SQL_DELETE_ENTRIES;

    public GoalEntryOpenHelper2(Context context) {

        super(context, GoalContract2.GoalEntry.TABLE_NAME2, null , 1);

    }
    public void onCreate(SQLiteDatabase db) {
        SQL_CREATE_ENTRIES =
                "CREATE TABLE " + GoalContract2.GoalEntry.TABLE_NAME2 + " (" +
                        GoalContract2.GoalEntry._ID + " INTEGER PRIMARY KEY," +
                        GoalContract2.GoalEntry.ENTER_GOAL + " TEXT," +
                        GoalContract2.GoalEntry.SPECIFICATIONS + " TEXT ,"+
                        GoalContract2.GoalEntry.Start_DATE + " TEXT ," +
                        GoalContract2.GoalEntry.End_DATE + " TEXT ," +
                        GoalContract2.GoalEntry.Start_TIME + " TEXT ," +
                        GoalContract2.GoalEntry.End_TIME+ " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.i("OpenHelper" , "database created");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + GoalContract2.GoalEntry.TABLE_NAME2;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
