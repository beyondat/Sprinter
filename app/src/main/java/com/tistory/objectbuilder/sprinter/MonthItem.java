package com.tistory.objectbuilder.sprinter;

/**
 * Created by dave on 2017. 7. 15..
 */

public class MonthItem {

    private int dayValue;

    public MonthItem() {
        //  nothing
    }

    public MonthItem(int day) {
        dayValue = day;
    }

    public int getDay() {
        return dayValue;
    }

    public void setDay(int day) {
        this.dayValue = day;
    }



}
