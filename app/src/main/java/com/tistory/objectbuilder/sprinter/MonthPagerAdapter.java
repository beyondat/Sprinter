package com.tistory.objectbuilder.sprinter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by dave on 2017. 7. 20..
 */

public class MonthPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{
    private Context mContext;
    public MonthAdapter monthViewAdapter;

    final static int pagerCnt=1000;
    final static int firstPosition = 500;

    int nowPosition;
    public MonthPagerAdapter(Context context){
        super();
        mContext = context;
    }

    public int getCount(){
        return pagerCnt;
    }

    @Override
    public Object instantiateItem(View pager, int position){
        Log.d("ITPANGPANG","Pre" + nowPosition + "NOW" + position);
        nowPosition = position;
        GridView grid = new GridView(mContext);
        grid.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT,GridView.LayoutParams.WRAP_CONTENT));
        grid.setColumnWidth(47);
        grid.setMinimumHeight(60);
        grid.setVerticalSpacing(0);
        grid.setNumColumns(7);
        grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        grid.setPadding(0, 20, 0, 0);


        monthViewAdapter = new MonthAdapter(mContext);
        grid.setAdapter(monthViewAdapter);
        ((ViewPager)pager).addView(grid);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                int day = curItem.getDay();

                Log.d("MainActivity", "Selected : " + day);
            }
        });

        return grid;
    }

    private void initGrid(){

    }

    @Override
    public void destroyItem(View pager, int position, Object view) {

        ((ViewPager) pager).removeView((View) view);

    }

    @Override

    public boolean isViewFromObject(View view, Object obj) {

        return view == obj;

    }



    @Override

    public void finishUpdate(View arg0) {

    }

    @Override

    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override

    public Parcelable saveState() {

        return null;

    }



    @Override

    public void startUpdate(View arg0) {

    }


    public void onPageScrollStateChanged(int state) {
        switch(state) {
            case ViewPager.SCROLL_STATE_IDLE:
                //HLog.d(TAG, CLASS, "SCROLL_STATE_IDLE");
                Log.d("SEtting","``````");
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
                //HLog.d(TAG, CLASS, "SCROLL_STATE_DRAGGING");
                //previousPosition = vvPager.getCurrentItem();
                Log.d("SEtting","!!!!!!!!");
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                //HLog.d(TAG, CLASS, "SCROLL_STATE_SETTLING");
                Log.d("SEtting","@@@@@@@@@");
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("SEtting","#####");
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("SEtting","$$$$$");
        if( position > nowPosition )
        {
            monthViewAdapter.setNextMonth();
            monthViewAdapter.notifyDataSetChanged();
        }
        else
        {
            monthViewAdapter.setPreviousMonth();
            monthViewAdapter.notifyDataSetChanged();
        }
        nowPosition = position;
    }
}
