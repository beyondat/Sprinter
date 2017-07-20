package com.tistory.objectbuilder.sprinter;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
//import java.util.jar.Manifest;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    GridView monthView;
    MonthAdapter monthViewAdapter;
    TextView monthText;
    int curYear;
    int curMonth;

    ListView listViewTodo;
    DetailTodoAdapter adapterTodo;


    public static final int REQ_CODE_ADD_TODO = 101;
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth + 1) + "월");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        mPager = (ViewPager)findViewById(R.id.pager);
//        mPager.setCurrentItem(500);
//        mPager.setAdapter(new MonthPagerAdapter(getApplicationContext()));
//        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
//            {
//                Log.d("ITPANGPANG","onPageScrolled : "+position);
//            }
//
//            @Override
//            public void onPageSelected(int position)
//            {
//                Log.d("ITPANGPANG","onPageSelected : "+position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state)
//            {
//                Log.d("ITPANGPANG","onPageScrollStateChanged : "+state);
//            }
//        });

        monthView = (GridView)findViewById(R.id.monthView);
        monthViewAdapter = new MonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);

        monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                int day = curItem.getDay();

                Log.d("MainActivity", "Selected : " + day);
            }
        });
        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();


        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
                //listViewTodo.removeAllViews();
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                start.set(monthViewAdapter.getCurYear(),monthViewAdapter.getCurMonth(),1,0,0);
                end.set(monthViewAdapter.getCurYear(),monthViewAdapter.getCurMonth(),getMonthLastDay(monthViewAdapter.getCurYear(),monthViewAdapter.getCurMonth()),1,0,0);
                DetailTodoAdapter adt = drawDetails(start, end);
                listViewTodo.setAdapter(adt);
                //adapterTodo.notifyDataSetChanged();
            }
        });


        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
                //listViewTodo.removeAllViews();
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                start.set(monthViewAdapter.getCurYear(),monthViewAdapter.getCurMonth(),1,0,0);
                end.set(monthViewAdapter.getCurYear(),monthViewAdapter.getCurMonth(),getMonthLastDay(monthViewAdapter.getCurYear(),monthViewAdapter.getCurMonth()),1,0,0);
                DetailTodoAdapter adt =
                        drawDetails(start, end);
                listViewTodo.setAdapter(adt);
                //adapterTodo.notifyDataSetChanged();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d("fab","pre intent");
                Intent intent = new Intent(getApplicationContext(), AddTodoActivity.class);
                startActivityForResult(intent, REQ_CODE_ADD_TODO);
                Log.d("fab","next intent");
            }
        });


        listViewTodo = (ListView)findViewById(R.id.todoView);
        adapterTodo = new DetailTodoAdapter();
        //adapterTodo.addItem(); Date 구조 확보 후 샘플 입력
//        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));

        //int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR);
        //final int callbackId = 42;
        //checkPermissions(callbackId, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR);

        final int MY_CAL_REQ = 42;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, MY_CAL_REQ);
        }


        //Uri uri = Uri.parse(contentUri+"/calendars");
/*        Cursor c = getContentResolver().query(uri,new String[]{"_id"},
                "selected=1", null,null);
        if(c==null || !c.moveToFirst())
        {
            Toast.makeText(this,"No calendar",Toast.LENGTH_LONG).show();
            Log.d("internal calendar", "no cal");
        }
        else
        {
            Log.d("internal calendar", "no cal");
            Toast.makeText(this,"got it!",Toast.LENGTH_LONG).show();
        }*/
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.set(startCal.get(startCal.YEAR),startCal.get(startCal.MONTH),1,0,0);
        endCal.set(endCal.get(startCal.YEAR),endCal.get(startCal.MONTH),getMonthLastDay(endCal.get(startCal.YEAR),endCal.get(startCal.MONTH)),0,0);
        DetailTodoAdapter adt = drawDetails(startCal, endCal);

        listViewTodo.setAdapter(adt);
        listViewTodo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DetailTodo item = (DetailTodo)adapterTodo.getItem(i);
                Log.d("List Adapter","num : "+i);
                // Activity 전환 -> 상세 Activity
            }
        });

    }

    private DetailTodoAdapter drawDetails(Calendar start, Calendar end){
        final String contentUri = "content://com.android.calendar";
        DetailTodoAdapter adt = new DetailTodoAdapter();
        String selection = "(( " + CalendarContract.Events.DTSTART + " >= " + start.getTimeInMillis() + " ) AND ( " + CalendarContract.Events.DTSTART + " <= " + end.getTimeInMillis() + " ))";

        Uri uri = Uri.parse(contentUri+"/events");
        String[] Projection = new String[]{"calendar_id", "title", "eventLocation", "dtstart"};
        Cursor c = getContentResolver().query(uri, Projection, selection,null,"dtstart ASC");
        if(c.moveToFirst()) {
            int[] calendar_id = new int[c.getCount()];
            String[] title = new String[c.getCount()];
            Date[] dateStart = new Date[c.getCount()];
            String[] eventLocation = new String[c.getCount()];

            for(int i=0; i<title.length; i++)
            {
                calendar_id[i] = c.getInt(0);
                title[i] = c.getString(1);
                int tmp = c.getInt(2);
                dateStart[i] = new Date(c.getInt(2));
                eventLocation[i] = c.getString(3);

                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd (E)");
                SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm a");
                String dt = sdf.format(new Date(c.getLong(3)));
                String ot = sdf1.format(new Date(c.getLong(3)));
                int len = title.length;

                if(title[i] != null)
                    adt.addItem(new DetailTodo(title[i],1,dt,ot,"관악구 봉천동"));
                c.moveToNext();
            }


        }
        adapterTodo = adt;
        return adt;
    }
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

    @Override
    public void onRequestPermissionsResult(int callbackId,
                                           String permissions[], int[] grantResults){

    }

    private void checkPermissions(int callbackId, String... permissionsId) {
        boolean permissions = true;
        for (String p : permissionsId) {
            permissions = permissions && ContextCompat.checkSelfPermission(this, p) == PackageManager.PERMISSION_GRANTED;

            if (!permissions)
                ActivityCompat.requestPermissions(this, permissionsId, callbackId);
        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK){
            Toast.makeText(getApplicationContext(), "일정이 등록처리 되었습니다.", Toast.LENGTH_LONG).show();
        }
    }


    class DetailTodoAdapter extends BaseAdapter {
        ArrayList<DetailTodo> items = new ArrayList<DetailTodo>();

        public int getCount() {
            return items.size();
        }

        public void addItem(DetailTodo _todo) {
            items.add(_todo);
        }

        public Object getItem(int p) {
            return items.get(p);
        }

        public long getItemId(int p) {
            return p;
        }

        public View getView(int position, View convertView, ViewGroup vg) {
            DetailTodoView view = new DetailTodoView(getApplicationContext());
            DetailTodo todo = items.get(position);
            view.setTextDetailsTitle(todo.getDate());
            view.setTextTime(todo.getAlram());
            view.setTextTodo(todo.getTodo());
            if (todo.getType() == 1) {
                view.setTextTodoType("이벤트");
                view.setImageTodoType(1);
            } else {
                view.setTextTodoType("할 일");
                view.setImageTodoType(2);
            }

            return view;
        }
    }



}