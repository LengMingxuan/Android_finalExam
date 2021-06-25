package com.lyulmx.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tvDebug;
    ListView mainlv;
    Button btnAllQK;
    List<Map<String, Object>> list;
    Cursor cursor;
    SQLiteDatabase db;
    SimpleAdapter adapter;
    MyOpenHelper helper = new MyOpenHelper(this);
    int clickListViewItemid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDebug = findViewById(R.id.tvDeBug);
        mainlv = findViewById(R.id.lv);
        btnAllQK = findViewById(R.id.btnAllQK);
        //TODO：(Finished!)链接控件与代码

        db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from users",null);

        mainlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                clickListViewItemid = position + 1;

                tvDebug.setText("您选择了："+ position);

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = new ArrayList<Map<String, Object>>();

        ProjectTools.GetData(list, cursor, db);
        //TODO: (Finished!)获取数据库数据

        adapter= new SimpleAdapter(
                this,list,R.layout.singlelistviewlayout,
                new String[]{"_id","StuId","Stuname"},
                new int[]{R.id.idStu,R.id.stuId,R.id.stuName});
        mainlv.setAdapter(adapter);
        //TODO：(Finished!)ListView 显示数据
    }


}