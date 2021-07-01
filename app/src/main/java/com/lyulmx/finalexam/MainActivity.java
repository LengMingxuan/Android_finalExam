package com.lyulmx.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tvDebug;
    ListView mainlv;
    Button btnAllQK,btnChangWeek;
    List<Map<String, Object>> list;
    Cursor cursor;
    SQLiteDatabase db;
    SimpleAdapter adapter;
    MyOpenHelper helper = new MyOpenHelper(this);
    int clickListViewItemid;
    Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int week = calendar.get(calendar.WEEK_OF_YEAR);
        int lastweeks = 27;
        tvDebug = findViewById(R.id.tvDeBug);
        mainlv = findViewById(R.id.lv);
        btnAllQK = findViewById(R.id.btnAllQK);
        btnChangWeek = findViewById(R.id.btnChangeWeek);
        //TODO：(Finished!)链接控件与代码


        db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from users",null);

        mainlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                clickListViewItemid = position + 1;

                Intent intent = new Intent(MainActivity.this, DetailedUserinfo_Activity.class);
                intent.putExtra("itemId",clickListViewItemid);
                startActivity(intent);
            }
        });
        //TODO: (Finished!)ListView 点击事件
        btnAllQK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllKq_Activity.class);
                startActivity(intent);
            }
        });
        btnChangWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1;i<=6;i++) {
                    db = helper.getReadableDatabase();
                    db.execSQL("update KQ set Mon2 = null,Mon3 = null,Tues1 = null,Tues2 = null,Tues3 = null,Tues4 = null," +
                            "Wen1 = null,Wen3 = null,Wen4 = null,Tur1 = null,Tur2 = null,Tur3 = null,Fir1 = null,Fir2 = null where _id =" + i);
                }
            }
        });

        if (week != lastweeks){
            for (int i = 1;i<=6;i++) {
                db = helper.getReadableDatabase();
                db.execSQL("update KQ set Mon2 = null,Mon3 = null,Tues1 = null,Tues2 = null,Tues3 = null,Tues4 = null," +
                        "Wen1 = null,Wen3 = null,Wen4 = null,Tur1 = null,Tur2 = null,Tur3 = null,Fir1 = null,Fir2 = null where _id =" + i);
            }
            lastweeks = week;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = new ArrayList<Map<String, Object>>();

        ProjectTools.GetData(list, cursor, db);
        //TODO: (Finished!)获取数据库数据

        adapter= new SimpleAdapter(
                this,list,R.layout.singlelistviewlayout,
                new String[]{"_id","StuId","Stuname","tx"},
                new int[]{R.id.idStu,R.id.stuId,R.id.stuName,R.id.userPhoto2});
        mainlv.setAdapter(adapter);
        //TODO：(Finished!)ListView 显示数据
    }



}