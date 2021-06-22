package com.lyulmx.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class EditItemInfoFrDb extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    EditText EDITAy_etName, EDITAy_etPhone, EDITAy_etStuId;
    Spinner sprBuild, sprRoom;
    RadioButton rbSex_boy, rbSex_girl;
    SQLiteDatabase db;
    MyOpenHelper helper = new MyOpenHelper(this);
    RadioGroup rgroup;
    Button btnUpdate;
    final String[] aprtBuilding = new String[] { "F1", "D4", "A1", "E3", "D10"};
    final String[] aprtRooms = new String[] { "617", "606", "618", "888"};
    String[] finalBuilding;
    String midRooms;
    String midBuilding;
    int newStuSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_info_fr_db);
        EDITAy_etName = findViewById(R.id.EEDITAy_etName);
        EDITAy_etPhone = findViewById(R.id.EDITAy_etPhone);
        EDITAy_etStuId = findViewById(R.id.EDITAy_etStuNum);
        sprBuild = findViewById(R.id.spinnerAprtBuildId);
        sprRoom = findViewById(R.id.spinnerAprtRoomId);
        rgroup = findViewById(R.id.rGroup);
        btnUpdate = findViewById(R.id.btnUpdate);


        ArrayAdapter<String> buildingAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, aprtBuilding);
        ArrayAdapter<String> roomsAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, aprtRooms);

        buildingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprRoom.setAdapter(roomsAdapter);
        sprBuild.setAdapter(buildingAdapter);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String strId = bundle.getString("strId");
        String strName = bundle.getString("strName");
        String strPhone = bundle.getString("strPhone");
        String strSex = bundle.getString("strSex");
        String strDprtmtNum = bundle.getString("strDprtmtNum");
        int idd = bundle.getInt("idd");


        EDITAy_etPhone.setText(strPhone);
        EDITAy_etStuId.setText(strId);
        EDITAy_etName.setText(strName);
        finalBuilding = strDprtmtNum.split("-");
        String arg1, arg2;
        arg1 = finalBuilding[0];
        arg2 = finalBuilding[1];
        setSpinnerDefaultValue(sprBuild,arg1);
        setSpinnerDefaultValue(sprRoom,arg2);
        String strid  = String.valueOf(idd);

        sprRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv1 = (TextView)view;
                midRooms = aprtRooms[position];
                //midRooms = tv1.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sprBuild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                //midBuilding = tv.getText().toString();
                midBuilding = aprtBuilding[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //TODO: spinner宿舍号传值

        rgroup.setOnCheckedChangeListener(this);

        setRBValue(strSex, rgroup);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = EDITAy_etName.getText().toString();
                String newPhone = EDITAy_etPhone.getText().toString();
                String newStuId = EDITAy_etStuId.getText().toString();
                db = helper.getReadableDatabase();
                db.execSQL("update users set Stuname='"+newName+"' where _id="+strid);
                db.execSQL("update users set Stuphone='"+newPhone+"' where _id="+strid);
                db.execSQL("update users set StuId='"+newStuId+"' where _id="+strid);
                String newBuildingAndRooms = midBuilding + "-" + midRooms;
                db.execSQL("update users set StuApartment='"+newBuildingAndRooms+"' where _id="+strid);
                db.execSQL("update users set StuSex='"+newStuSex+"' where _id="+strid);
                Intent intent = new Intent(EditItemInfoFrDb.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void setSpinnerDefaultValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter();
        int size = apsAdapter.getCount();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(value, apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i,true);
                break;
            }
        }
    }

    private void setRBValue(String sexStr, RadioGroup typeRadioGroup){
        if(sexStr.equals("0")){//代表男的
            typeRadioGroup.check(R.id.rbBoy);
        }else if(sexStr.equals("1")){//代表女的
            typeRadioGroup.check(R.id.rbGirl);
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        selectRadioBtn();
    }

    private void selectRadioBtn() {
        RadioButton radioButton = (RadioButton) findViewById(rgroup.getCheckedRadioButtonId());
        if (radioButton.getText().toString().equals("男")){
            newStuSex = 0;
        }else{
            newStuSex = 1;
        }

    }
    //TODO: RadioButton性别传值



}