package com.tistory.objectbuilder.sprinter;

import java.util.Date;

/**
 * Created by dave on 2017. 7. 16..
 */

public class DetailTodo {
    String todo;
    int type;
    //Date date;
    String date;    //  Date로 확보해야함
    String alram;      //  Time으로 확보해야함
    String position;

    public DetailTodo(){

    }

    public DetailTodo(String _todo, int _type, String _date, String _alram, String _position){
        todo = _todo;
        type = _type;
        date = _date;
        alram = _alram;
        position = _position;
    }

    public void setTodo(String str){todo=str;}
    public void setType(int i){type=i;}
    public void setDate(String d){date = d;}
    public void setAlram(String str){alram = str;}
    public void setPosition(String p){position=p;}

    public String getTodo(){return todo;}
    public int getType(){return type;}
    public String getDate(){return date;}
    public String getAlram(){return alram;}
    public String getPosition(){return position;}

}
