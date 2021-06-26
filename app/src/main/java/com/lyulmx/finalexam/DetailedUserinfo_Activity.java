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
    Cursor cursor,cursor1;
    SQLiteDatabase db;
    TextView stuId, stuName, stuSex, stuApartmentNum, stuPhone;
    MyOpenHelper helper = new MyOpenHelper(this);
    String strId, strName, strSex, strApartmentNum, strPhone;
    Button btnEdit, btnBack;

    Button btnMon_WLAQ,btnMin_WLGC,
            btnTes_ARM,btnTes_JSP,btnTes_VCPP,btnTes_RFID,
            btnWes_WLAQ,btnWes_ARM,btnWes_And,
            btnTur_RFID,btnTur_VCPP,btnTur_QYSX,
            btnFir_JSP,btnFir_And;


    String[] AllClassWeek = new String[]{"Mon2","Mon3","Tues1","Tues2","Tues3","Tues4","Wen1",
            "Wen3","Wen4","Tur1","Tur2","Tur3","Fir1","Fir2"};

    int[] ClickBtnFlags = new int[15];

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_userinfo);

        stuId = findViewById(R.id.ADU_stuID);
        stuName = findViewById(R.id.ADU_stuName);
        stuSex = findViewById(R.id.ADU_stuSex);
        stuApartmentNum = findViewById(R.id.ADU_stuDepartmentNum);
        stuPhone = findViewById(R.id.ADU_stuPhone);
        btnBack = findViewById(R.id.btnBackAduToListview);
        btnEdit = findViewById(R.id.btnEdit);
        btnMon_WLAQ = findViewById(R.id.btnMon_WLAQ);
        btnMin_WLGC = findViewById(R.id.btnMin_WLGC);
        btnTes_ARM = findViewById(R.id.btnTes_ARM);
        btnTes_JSP = findViewById(R.id.btnTes_JSP);
        btnTes_VCPP = findViewById(R.id.btnTes_VCPP);
        btnTes_RFID = findViewById(R.id.btnTes_RFID);
        btnWes_ARM  = findViewById(R.id.btnWes_ARM);
        btnWes_And = findViewById(R.id.btnWes_And);
        btnWes_WLAQ = findViewById(R.id.btnWes_WLAQ);
        btnTur_RFID = findViewById(R.id.btnTur_RFID);
        btnTur_VCPP = findViewById(R.id.btnTur_VCPP);
        btnTur_QYSX = findViewById(R.id.btnTur_QYSX);
        btnFir_And = findViewById(R.id.btnFir_And);
        btnFir_JSP = findViewById(R.id.btnFir_JSP);
        //TODO: (Finished!)控件链接

        Button[] btnRid = new Button[]{btnMon_WLAQ, btnMin_WLGC, btnTes_ARM, btnTes_JSP, btnTes_VCPP,
                btnTes_RFID, btnWes_WLAQ,btnWes_ARM, btnWes_And, btnTur_RFID, btnTur_VCPP, btnTur_QYSX,
                btnFir_JSP,btnFir_And };

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        EUA_itemId = bundle.getInt("itemId");
        //TODO: (Finished!)获取用户点击的itemID

        db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from users where _id=?", new String[]{String.valueOf(EUA_itemId)});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                strId = cursor.getString(cursor.getColumnIndex("StuId"));
                strName = cursor.getString(cursor.getColumnIndex("Stuname"));
                strPhone = cursor.getString(cursor.getColumnIndex("Stuphone"));
                strSex = cursor.getString(cursor.getColumnIndex("StuSex"));
                strApartmentNum = cursor.getString(cursor.getColumnIndex("StuApartment"));
            }
        }
        //TODO: (Finished!)在数据库中查找数据并赋值给变量

        stuName.setText("" + strName);
        stuId.setText("" + strId);
        stuApartmentNum.setText("" + strApartmentNum);
        stuPhone.setText("" + strPhone);
        if (strSex.equals("0")) {
            stuSex.setText("男");
        } else {
            stuSex.setText("女");
        }
        //TODO: (Finished!)将得到的数据显示在控件上

        btnBack.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnMon_WLAQ.setOnClickListener(this);
        btnMin_WLGC.setOnClickListener(this);
        btnTes_ARM.setOnClickListener(this);
        btnTes_JSP.setOnClickListener(this);
        btnTes_VCPP.setOnClickListener(this);
        btnTes_RFID.setOnClickListener(this);
        btnWes_ARM.setOnClickListener(this);
        btnWes_And.setOnClickListener(this);
        btnWes_WLAQ.setOnClickListener(this);
        btnTur_RFID.setOnClickListener(this);
        btnTur_VCPP.setOnClickListener(this);
        btnTur_QYSX.setOnClickListener(this);
        btnFir_And.setOnClickListener(this);
        btnFir_JSP.setOnClickListener(this);

        //TODO: (Finished!)用户按钮点击事件

        int ii;
        for (int j = 0; j < 14; j++)
        {
            cursor = db.rawQuery("select * from KQ where _id=?", new String[]{String.valueOf(EUA_itemId)});
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    ii = cursor.getInt(cursor.getColumnIndex(AllClassWeek[j]));
                    if (ii == 2){
                        btnRid[j].setBackgroundResource(R.drawable.btnrc2);
                        //ClickBtnFlags[j] =1;
                    }
                    else if (ii == 3){
                        btnRid[j].setBackgroundResource(R.drawable.btnrc3);
                        //ClickBtnFlags[j] =0;
                    }
                    else if (ii == 1){
                        btnRid[j].setBackgroundResource(R.drawable.btnrc1);
                        //ClickBtnFlags[j] =1;
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        int temp = ProjectTools.ReturnIdByViewid(v);
        switch (v.getId()) {
            case R.id.btnBackAduToListview:
                Intent intent1 = new Intent(DetailedUserinfo_Activity.this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnEdit:
                Intent intent = new Intent(DetailedUserinfo_Activity.this, EditItemInfoFrDb.class);
                intent.putExtra("strId", strId);
                intent.putExtra("strName", strName);
                intent.putExtra("strPhone", strPhone);
                intent.putExtra("strSex", strSex);
                intent.putExtra("strDprtmtNum", strApartmentNum);
                intent.putExtra("idd", EUA_itemId);
                startActivity(intent);
                break;
            case R.id.btnMon_WLAQ:case R.id.btnMin_WLGC:
            case R.id.btnTes_ARM:case  R.id.btnTes_JSP:case R.id.btnTes_VCPP:case R.id.btnTes_RFID:
            case R.id.btnWes_ARM:case R.id.btnWes_And:case  R.id.btnWes_WLAQ:
            case R.id.btnTur_RFID:case R.id.btnTur_QYSX:case R.id.btnTur_VCPP:
            case R.id.btnFir_And:case R.id.btnFir_JSP:
                String getClassIdByViewid = ProjectTools.getClassIdByViewid(v.getId());
                ClickBtnFlags[temp]++;
                if (ClickBtnFlags[temp] % 3 == 1) {

                    btn.setBackgroundResource(R.drawable.btnrc1);
                    btn.setTextColor(0xFF000000);
                    db.execSQL("update KQ set "+getClassIdByViewid+"=1 where _id="+EUA_itemId);
                } else if (ClickBtnFlags[temp] % 3 == 2) {
                    btn.setBackgroundResource(R.drawable.btnrc2);
                    btn.setTextColor(0xFF000000);
                    db.execSQL("update KQ set "+getClassIdByViewid+"=2 where _id="+EUA_itemId);
                } else if (ClickBtnFlags[temp] % 3 == 0) {
                    btn.setBackgroundResource(R.drawable.btnrc3);
                    btn.setTextColor(0xFFFFFFFF);
                    db.execSQL("update KQ set "+getClassIdByViewid+"=3 where _id="+EUA_itemId);
                }
        }
    }

    //TODO: (Finished!)用户按钮点击事件实现
}