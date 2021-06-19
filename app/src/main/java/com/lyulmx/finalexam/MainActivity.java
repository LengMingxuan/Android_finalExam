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
    SimpleAdapter adapter;
    MyOpenHelper helper = new MyOpenHelper(this);;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from users",null);
        mainlv = findViewById(R.id.lv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = new ArrayList<Map<String, Object>>();
        GetData();
        adapter= new SimpleAdapter(
                this,list,R.layout.singlelistviewlayout,
                new String[]{"_id","StuId","Stuname"},
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
                String studentId = cursor.getString(cursor.getColumnIndex("StuId"));
                String studentName = cursor.getString(cursor.getColumnIndex("Stuname"));

                map.put("_id",id);
                map.put("StuId",studentId);
                map.put("Stuname",studentName);

                list.add(map);
            }
        }
        cursor.close();
        db.close();
    }
}