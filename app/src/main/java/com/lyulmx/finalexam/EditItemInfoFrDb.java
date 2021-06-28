package com.lyulmx.finalexam;

import androidx.appcompat.app.AppCompatActivity;

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

public class EditItemInfoFrDb extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    EditText EDITAy_etName, EDITAy_etPhone, EDITAy_etStuId;
    Spinner sprBuild, sprRoom;
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
    boolean SpinnerBuildingChangedFlag = false, SpinnerRoomsChangedFlag = false;
    //TODO: (Finished!)定义变量

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
        //TODO: (Finished!)链接控件


        ArrayAdapter<String> buildingAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, aprtBuilding);
        ArrayAdapter<String> roomsAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, aprtRooms);

        buildingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprRoom.setAdapter(roomsAdapter);
        sprBuild.setAdapter(buildingAdapter);
        //TODO: (Finished!)两个Spinner的数据填充

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String strId = bundle.getString("strId");
        String strName = bundle.getString("strName");
        String strPhone = bundle.getString("strPhone");
        String strSex = bundle.getString("strSex");
        String strDprtmtNum = bundle.getString("strDprtmtNum");
        int idd = bundle.getInt("idd");
        String strid  = String.valueOf(idd);
        //TODO: (Finished!)获取中页面传来的各种学生详情数据


        EDITAy_etPhone.setText(strPhone);
        EDITAy_etStuId.setText(strId);
        EDITAy_etName.setText(strName);
        finalBuilding = strDprtmtNum.split("-");
        String arg1, arg2;
        arg1 = finalBuilding[0];
        arg2 = finalBuilding[1];
        setSpinnerDefaultValue(sprBuild,arg1);  //设置Spinner默认数据
        setSpinnerDefaultValue(sprRoom,arg2);
        //TODO: (Finished!) 将传来的数据显示在各种控件上


        sprRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TextView tv1 = (TextView)view;
                midRooms = aprtRooms[position];
                //midRooms = tv1.getText().toString();
                SpinnerRoomsChangedFlag = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sprBuild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TextView tv = (TextView)view;
                //midBuilding = tv.getText().toString();
                midBuilding = aprtBuilding[position];
                SpinnerBuildingChangedFlag = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //TODO: spinner宿舍号传值，（待实现）不改变Spinner的值会显示null!!!

        rgroup.setOnCheckedChangeListener(this);
        //TODO: (Finished!)RadioGroup选择改变监听 外部处理事件

        setRBValue(strSex, rgroup);
        //TODO: (Finished!)(自定义-定义)RadioButton传值函数 -> 根据选择的RadioButton男女，返回0和1

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

                if (SpinnerBuildingChangedFlag && SpinnerRoomsChangedFlag){
                    db.execSQL("update users set StuApartment='"+newBuildingAndRooms+"' where _id="+strid);
                    }
                else if (SpinnerBuildingChangedFlag && !SpinnerRoomsChangedFlag){
                    db.execSQL("update users set StuApartment='"+midBuilding+"-"+arg2+"' where _id="+strid);
                    }
                else if(!SpinnerBuildingChangedFlag && SpinnerRoomsChangedFlag){
                    db.execSQL("update users set StuApartment='"+arg1+"-"+midRooms+"' where _id="+strid);
                    }
                else{
                    db.execSQL("update users set StuApartment='"+newBuildingAndRooms+"' where _id="+strid);
                    }
                SpinnerRoomsChangedFlag = false;
                SpinnerBuildingChangedFlag = false;
                db.execSQL("update users set StuSex='"+newStuSex+"' where _id="+strid);
                Intent intent = new Intent(EditItemInfoFrDb.this,MainActivity.class);
                startActivity(intent);
                finish();
                db.close();
            }
        });
        //TODO: (Finished!)更新按钮按键监听事件

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
    //TODO: (Finished!)（自定义-实现）设置Spinner默认数据

    private void setRBValue(String sexStr, RadioGroup typeRadioGroup){
        if(sexStr.equals("0")){//代表男的
            typeRadioGroup.check(R.id.rbBoy);
        }else if(sexStr.equals("1")){//代表女的
            typeRadioGroup.check(R.id.rbGirl);
        }
    }
    //TODO: (Finished!)(自定义-实现)RadioButton传值函数 -> 根据选择的RadioButton男女，返回0和1

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
    //TODO: (Finished!) RadioButton性别传值

}