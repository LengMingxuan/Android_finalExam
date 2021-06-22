package com.lyulmx.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailedUserinfo_Activity extends AppCompatActivity implements View.OnClickListener {

    int EUA_itemId;
    Cursor cursor;
    SQLiteDatabase db;
    TextView stuId, stuName, stuSex, stuDprtmtNum, stuPhone;
    MyOpenHelper helper = new MyOpenHelper(this);
    String strId, strName, strSex, strDprtmtNum, strPhone;
    Button  btnEdit, btnBack;

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
        btnBack = findViewById(R.id.btnBackAduToListview);
        btnEdit = findViewById(R.id.btnEdit);
        //TODO: (Finished!)控件链接

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        EUA_itemId = bundle.getInt("itemId");
        //TODO: (Finished!)获取用户点击的itemID

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
        //TODO: (Finished!)在数据库中查找数据并赋值给变量

        stuName.setText("" + strName);
        stuId.setText("" + strId);
        stuDprtmtNum.setText("" + strDprtmtNum);
        stuPhone.setText("" + strPhone);
        if (strSex.equals("0")){
            stuSex.setText("男");
        }
        else{
            stuSex.setText("女");
        }
        //TODO: (Finished!)将得到的数据显示在控件上

        btnBack.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
       //TODO: (Finished!)用户按钮点击事件
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBackAduToListview:
                Intent intent1 = new Intent(DetailedUserinfo_Activity.this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnEdit:
                Intent intent = new Intent(DetailedUserinfo_Activity.this, EditItemInfoFrDb.class);
                intent.putExtra("strId",strId);
                intent.putExtra("strName",strName);
                intent.putExtra("strPhone",strPhone);
                intent.putExtra("strSex",strSex);
                intent.putExtra("strDprtmtNum",strDprtmtNum);
                intent.putExtra("idd",EUA_itemId);
                startActivity(intent);
                break;
        }
    }
    //TODO: (Finished!)用户按钮点击事件实现
}