package com.example.rahil.StrikerProject;

import android.util.Log;

/**
 * Created by RAHIL on 11/02/2018.
 */

public class Goals {

    String name;
    String specs;
    String beginTime;
    String endtime;
    String beginDate;
    String endDate;
    String _id;

    public String get_id() {
        Log.e("Goals", "the id is" + _id);
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public Goals(String name, String specs, String beginTime, String endtime, String beginDate, String endDate, String _id) {
        this.name = name;
        this.specs = specs;
        this.beginTime = beginTime;
        this.endtime = endtime;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this._id = _id;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getName() {
        return name;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndtime() {
        return endtime;
    }


    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }
}
