package com.example.harshagoli.networkinggame;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TimerModel extends RealmObject {


    @PrimaryKey
    private String ID;
    private String Year;
    private String Month;
    private String DayOfMonth;
    private String HourOfDay;
    private String Minute;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getDayOfMonth() {
        return DayOfMonth;
    }

    public void setDayOfMonth(String dayOfMonth) {
        DayOfMonth = dayOfMonth;
    }

    public String getHourOfDay() {
        return HourOfDay;
    }

    public void setHourOfDay(String hourOfDay) {
        HourOfDay = hourOfDay;
    }

    public String getMinute() {
        return Minute;
    }

    public void setMinute(String minute) {
        Minute = minute;
    }





}
