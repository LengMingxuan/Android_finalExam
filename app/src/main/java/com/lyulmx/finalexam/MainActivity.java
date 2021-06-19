package com.lyulmx.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView mainlv;
    List<Map<String, Object>> list;
    Cursor cursor;
    SQLiteDatabase db;
    MyOpenHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<Map<String, Object>>();
        setContentView(R.layout.activity_main);
        mainlv = findViewById(R.id.lv);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SimpleAdapter adapter = new SimpleAdapter(
                this,list,R.layout.activity_main,
                new String[]{"_id","name","phone"},
                new int[]{R.id.idStu,R.id.stuId,R.id.stuName});
        mainlv.setAdapter(adapter);
    }

    public void GetData(){

        list.clear();

        Map<String, Object> map = null;
        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                map = new HashMap<String, Object>();
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));

                map.put("_id",id);
                map.put("name",name);
                map.put("phone",phone);

                list.add(map);
            }
        }
        cursor.close();
        db.close();
    }
}