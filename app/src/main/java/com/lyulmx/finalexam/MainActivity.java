package com.lyulmx.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
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

        mainlv = findViewById(R.id.lv);
        //TODO：链接控件与代码

        db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from users",null);

        mainlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        //TODO: ListView 点击事件
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = new ArrayList<Map<String, Object>>();

        GetListViewItemData.GetData(list, cursor, db);
        //TODO: 获取数据库数据

        adapter= new SimpleAdapter(
                this,list,R.layout.singlelistviewlayout,
                new String[]{"_id","StuId","Stuname"},
                new int[]{R.id.idStu,R.id.stuId,R.id.stuName});
        mainlv.setAdapter(adapter);
        //TODO：ListView 显示数据
    }


}