package com.tistory.objectbuilder.sprinter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by dave on 2017. 7. 21..
 */

public class AddTodoActivity extends AppCompatActivity {
    final int PICK_DATE = 1;
    final int PICK_TIME = 2;
    EditText edtDate;
    EditText edtTime;
    protected void onCreate(Bundle savedInstanceStae){
        super.onCreate(savedInstanceStae);
        setContentView(R.layout.activity_add_todo);

        Button buttonReg = (Button)findViewById(R.id.buttonRegist);
        buttonReg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        edtDate = (EditText)findViewById(R.id.editAddDate);
        edtDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showDialog(PICK_DATE);
            }
        });

        edtTime = (EditText)findViewById(R.id.editAddAlramTime);
        edtTime.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showDialog(PICK_TIME);
            }
        });
    }

    protected Dialog onCreateDialog(int id){
        switch(id){
            case PICK_DATE:
                Calendar cal = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog
                        (this, // 현재화면의 제어권자
                                new DatePickerDialog.OnDateSetListener() {
                                    public void onDateSet(DatePicker view,
                                                          int year, int monthOfYear,int dayOfMonth) {
                                        edtDate.setText(year+"년"+monthOfYear+"월"+dayOfMonth+"일");
                                    }
                                }
                                , // 사용자가 날짜설정 후 다이얼로그 빠져나올때
                                //    호출할 리스너 등록
                                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)); // 기본값 연월일
                return dpd;

            case PICK_TIME:
                TimePickerDialog tpd =
                        new TimePickerDialog(this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view,
                                                          int hourOfDay, int minute) {
                                        edtTime.setText(hourOfDay+"시"+minute+"분");

                                    }
                                }, // 값설정시 호출될 리스너 등록
                                0,0, false); // 기본값 시분 등록
                // true : 24 시간(0~23) 표시
                // false : 오전/오후 항목이 생김
                return tpd;

        }
        return super.onCreateDialog(id);
    }
}
