package com.lyulmx.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class DetailedUserinfo_Activity extends AppCompatActivity {

    int EUA_itemId;
    Cursor cursor;
    SQLiteDatabase db;
    TextView stuId, stuName, stuSex, stuDprtmtNum, stuPhone;
    MyOpenHelper helper = new MyOpenHelper(this);
    String strId, strName, strSex, strDprtmtNum, strPhone;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_userinfo);

        stuId = findViewById(R.id.ADU_stuID);
        stuName =findViewById(R.id.ADU_stuName);
        stuSex = findViewById(R.id.ADU_stuSex);
        stuDprtmtNum = findViewById(R.id.ADU_stuDepartmentNum);
        stuPhone = findViewById(R.id.ADU_stuPhone);
        //TODO: 控件链接

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        EUA_itemId = bundle.getInt("itemId");
        //TODO: 获取用户点击的itemID

        db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from users where _id=?", new String[]{String.valueOf(EUA_itemId)});
        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                strId = cursor.getString(cursor.getColumnIndex("StuId"));
                strName = cursor.getString(cursor.getColumnIndex("Stuname"));
                strPhone = cursor.getString(cursor.getColumnIndex("Stuphone"));
                strSex = cursor.getString(cursor.getColumnIndex("StuSex"));
                strDprtmtNum = cursor.getString(cursor.getColumnIndex("StuApartment"));
            }
        }
        //TODO: 在数据库中查找数据并赋值给变量

        stuName.setText("" + strName);
        stuId.setText("" + strId);
        stuDprtmtNum.setText("" + strDprtmtNum);
        stuPhone.setText("" + strPhone);
       if (strSex.equals("0")){
            stuSex.setText("男");
        }else{
            stuSex.setText("女");
        }
        //TODO: 将得到的数据显示在控件上
    }

}