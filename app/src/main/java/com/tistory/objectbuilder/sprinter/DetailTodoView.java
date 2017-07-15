package com.tistory.objectbuilder.sprinter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by dave on 2017. 7. 16..
 */

public class DetailTodoView extends LinearLayout {
    TextView textDetailsTitle;
    TextView textTodo;
    TextView textTime;
    TextView textTodoType;
    ImageView imageTodoType;

    public DetailTodoView(Context context){
        super(context);
        init(context);
    }

    public DetailTodoView(Context context, AttributeSet attrs){
        super(context);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.detatil_todo, this, true);

        textDetailsTitle = (TextView)findViewById(R.id.textDetailsTitle);
        textTodo = (TextView)findViewById(R.id.textTodo);
        textTime = (TextView)findViewById(R.id.textTime);
        textTodoType = (TextView)findViewById(R.id.textTodoType);
        imageTodoType = (ImageView)findViewById(R.id.imageTodoType);
    }

    public void setTextDetailsTitle(String str){ textDetailsTitle.setText(str); }
    public void setTextTodo(String str){ textTodo.setText(str);}
    public void setTextTime(String str){textTime.setText(str);}
    public void setTextTodoType(String str){textTodoType.setText(str);}
    public void setImageTodoType(int resId){imageTodoType.setImageResource(R.drawable.ic_oval_todo_24_dp);}

}
