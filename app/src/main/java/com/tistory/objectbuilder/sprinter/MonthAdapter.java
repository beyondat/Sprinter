package com.tistory.objectbuilder.sprinter;

import android.content.Context;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by dave on 2017. 7. 15..
 */

public class MonthAdapter extends BaseAdapter {

    private MonthItem[] items;
    public static final String TAG = "MonthAdapter";
    Context mContext;
    public static int oddColor = Color.rgb(225, 225, 225);
    public static int headColor = Color.rgb(12, 32, 158);
    Calendar mCalendar;
    int firstDay;
    int mStartDay;
    int startDay;
    int curYear;
    int curMonth;
    int lastDay;
    int selectedPosition = -1;
    int countColumn = 7;

    public MonthAdapter(Context context) {
        super();
        mContext = context;
        init();
    }

    public MonthAdapter(Context context, AttributeSet attrs){
        super();
        mContext = context;
        init();
    }

    private void init(){
        items = new MonthItem[7*6];
        mCalendar = Calendar.getInstance();
        recalculate();
        resetDayNumbers();
    }

    public void recalculate(){
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);

        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        firstDay = getFirstDay(dayOfWeek);

        mStartDay = mCalendar.getFirstDayOfWeek();
        curYear = mCalendar.get(Calendar.YEAR);
        curMonth = mCalendar.get(Calendar.MONTH);
        lastDay = getMonthLastDay(curYear, curMonth);

        int diff = mStartDay - Calendar.SUNDAY -1;
        startDay = getFirstDayOfWeek();
    }

    public void setPreviousMonth(){
        mCalendar.add(Calendar.MONDAY, -1);
        recalculate();
        resetDayNumbers();
        selectedPosition = -1;
    }

    public void resetDayNumbers(){
        for(int i=0; i<42; i++){
            int dayNumber = (i+1) - firstDay;
            if(dayNumber < 1 || dayNumber > lastDay){
                dayNumber = 0;
            }
            items[i] = new MonthItem(dayNumber);
        }
    }
    private int getFirstDay(int dayOfWeek) {
        int result = 0;
        if (dayOfWeek == Calendar.SUNDAY) {
            result = 0;
        } else if (dayOfWeek == Calendar.MONDAY) {
            result = 1;
        } else if (dayOfWeek == Calendar.TUESDAY) {
            result = 2;
        } else if (dayOfWeek == Calendar.WEDNESDAY) {
            result = 3;
        } else if (dayOfWeek == Calendar.THURSDAY) {
            result = 4;
        } else if (dayOfWeek == Calendar.FRIDAY) {
            result = 5;
        } else if (dayOfWeek == Calendar.SATURDAY) {
            result = 6;
        }

        return result;
    }


    public int getCurYear() {
        return curYear;
    }

    public int getCurMonth() {
        return curMonth;
    }


    public int getNumColumns() {
        return 7;
    }

    public int getCount() {
        return 7 * 6;
    }

    public Object getItem(int position) {
        return items[position];
    }

    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        MonthItemView itemView;
        if(convertView == null){
            itemView = new MonthItemView(mContext);
        }else{
            itemView = (MonthItemView) convertView;
        }

        GridView.LayoutParams params = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, 70);
        int rowIndex = position / countColumn;
        int columnIndex = position % countColumn;

        itemView.setItem(items[position]);
        itemView.setLayoutParams(params);
        itemView.setPadding(2,2,2,2);

        itemView.setGravity(Gravity.LEFT);

        if(columnIndex ==0 ){
            itemView.setTextColor(Color.RED);
        }else{
            itemView.setTextColor(Color.BLACK);

        }

        if(position == getSelectedPosition()){
            itemView.setBackgroundColor(Color.YELLOW);
        }else{
            itemView.setBackgroundColor(Color.WHITE);
        }

        return itemView;

    }



    public static int getFirstDayOfWeek() {
        int startDay = Calendar.getInstance().getFirstDayOfWeek();
        if (startDay == Calendar.SATURDAY) {
            return Time.SATURDAY;
        } else if (startDay == Calendar.MONDAY) {
            return Time.MONDAY;
        } else {
            return Time.SUNDAY;
        }
    }


    /**
     * get day count for each month
     *
     * @param year
     * @param month
     * @return
     */
    private int getMonthLastDay(int year, int month){
        switch (month) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return (31);

            case 3:
            case 5:
            case 8:
            case 10:
                return (30);

            default:
                if(((year%4==0)&&(year%100!=0)) || (year%400==0) ) {
                    return (29);   // 2�� �ㅻ뀈怨꾩궛
                } else {
                    return (28);
                }
        }
    }

    /**
     * set selected row
     */
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    /**
     * get selected row
     *
     * @return
     */
    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setNextMonth() {
        mCalendar.add(Calendar.MONTH, 1);
        recalculate();

        resetDayNumbers();
        selectedPosition = -1;
    }

}
