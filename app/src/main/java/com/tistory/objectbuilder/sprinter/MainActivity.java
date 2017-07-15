package com.tistory.objectbuilder.sprinter;

import android.os.Bundle;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    GridView monthView;
    MonthAdapter monthViewAdapter;
    TextView monthText;
    int curYear;
    int curMonth;

    ListView listViewTodo;
    DetailTodoAdapter adapterTodo;

    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth + 1) + "월");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            }
        });


        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        listViewTodo = (ListView)findViewById(R.id.todoView);
        adapterTodo = new DetailTodoAdapter();
        //adapterTodo.addItem(); Date 구조 확보 후 샘플 입력
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));
        adapterTodo.addItem(new DetailTodo("중구 생일",1,"1월 11일 월요일",1111,"관악구 봉천동"));

        listViewTodo.setAdapter(adapterTodo);
        listViewTodo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DetailTodo item = (DetailTodo)adapterTodo.getItem(i);

                // Activity 전환 -> 상세 Activity
            }
        });

    }


    class DetailTodoAdapter extends BaseAdapter{
        ArrayList<DetailTodo> items = new ArrayList<DetailTodo>();

        public int getCount(){return items.size(); }

        public void addItem(DetailTodo _todo){
            items.add(_todo);
        }

        public Object getItem(int p){ return items.get(p);}

        public long getItemId(int p){ return p;}

        public View getView(int position, View convertView, ViewGroup vg){
            DetailTodoView view = new DetailTodoView(getApplicationContext());
            DetailTodo todo = items.get(position);
            view.setTextDetailsTitle("1월 11일 월요일 일정입니다");
            view.setTextTime(Integer.toString(todo.getAlram()));
            view.setTextTodo(todo.getTodo());
            if(todo.getType() == 1){
                view.setTextTodoType("이벤트");
                view.setImageTodoType(1);
            }else{
                view.setTextTodoType("할 일");
                view.setImageTodoType(2);
            }

            return view;
        }
    }
}