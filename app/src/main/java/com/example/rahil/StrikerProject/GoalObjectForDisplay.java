package com.example.rahil.StrikerProject;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by RAHIL on 06/03/2018.
 */

public class GoalObjectForDisplay implements Parcelable {
    String ENTER_GOAL;
    String SPECIFICATIONS;
    String End_DATE;
    String End_TIME;
    String Start_DATE;
    String Start_TIME;
    String _id;
    String whichTab;

    public String getWhichTab() {
        return whichTab;
    }

    public void setWhichTab(String whichTab) {
        this.whichTab = whichTab;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public GoalObjectForDisplay() {
        super();
    }

    public String getENTER_GOAL() {
        return ENTER_GOAL;
    }

    public void setENTER_GOAL(String ENTER_GOAL) {
        this.ENTER_GOAL = ENTER_GOAL;
    }

    public String getSPECIFICATIONS() {
        return SPECIFICATIONS;
    }

    public void setSPECIFICATIONS(String SPECIFICATIONS) {
        this.SPECIFICATIONS = SPECIFICATIONS;
    }

    public String getEnd_DATE() {
        return End_DATE;
    }

    public void setEnd_DATE(String end_DATE) {
        End_DATE = end_DATE;
    }

    public String getEnd_TIME() {
        return End_TIME;
    }

    public void setEnd_TIME(String end_TIME) {
        End_TIME = end_TIME;
    }

    public String getStart_DATE() {
        return Start_DATE;
    }

    public void setStart_DATE(String start_DATE) {
        Start_DATE = start_DATE;
    }

    public String getStart_TIME() {
        return Start_TIME;
    }

    public void setStart_TIME(String start_TIME) {
        Start_TIME = start_TIME;
    }

    public GoalObjectForDisplay(Parcel p) {
        this.ENTER_GOAL = p.readString();
        this.SPECIFICATIONS = p.readString();
        this.End_DATE = p.readString();
        this.End_TIME = p.readString();
        this.Start_DATE = p.readString();
        this.Start_TIME = p.readString();
        this._id = p.readString();
        this.whichTab = p.readString();

        Log.e("GoalObjectForDisplay" , "Values fetched in Constructor");
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ENTER_GOAL);
        dest.writeString(this.SPECIFICATIONS);
        dest.writeString(this.End_DATE);
        dest.writeString(this.End_TIME);
        dest.writeString(this.Start_DATE);
        dest.writeString(this.Start_TIME);
        dest.writeString(this._id);
        dest.writeString(this.whichTab);
        Log.e("GoalObjectForDisplay" , "writing to parcel");

    }


    public static final Creator<GoalObjectForDisplay> CREATOR = new Creator<GoalObjectForDisplay>() {
        @Override
        public GoalObjectForDisplay createFromParcel(Parcel source) {
            Log.e("GoalObjectForDisplay" , "returning from parcel");

            return new GoalObjectForDisplay(source);
        }

        @Override
        public GoalObjectForDisplay[] newArray(int size) {
            Log.e("GoalObjectForDisplay" , "returning from array thing");

            return new GoalObjectForDisplay[size];
        }
    };
}
