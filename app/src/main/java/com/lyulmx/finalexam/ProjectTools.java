package com.lyulmx.finalexam;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectTools {

    public static void GetData(List<Map<String, Object>> list, Cursor cursor, SQLiteDatabase db){

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
    //TODO: (Finished!)获取该显示在ListView该显示的三种类型数据

    public static void GetAllDataFromDB(Cursor cursor, SQLiteDatabase db,int itemId) {

        cursor = db.rawQuery("select * from student where id=?", new String[]{String.valueOf(itemId)});
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
        }
    }
    //TODO: (Finished!)(未使用)获取数据库的所有数据

    @SuppressLint("NonConstantResourceId")
    public static int ReturnIdByViewid(View v){
        int finallyResult = -1;
        switch (v.getId()){
            case R.id.btnMon_WLAQ:
                finallyResult = 0;
                break;
            case R.id.btnMin_WLGC:
                finallyResult = 1;
                break;
            case R.id.btnTes_ARM:
                finallyResult = 2;
                break;
            case  R.id.btnTes_JSP:
                finallyResult = 3;
                break;
            case R.id.btnTes_VCPP:
                finallyResult = 4;
                break;
            case R.id.btnTes_RFID:
                finallyResult = 13;
                break;
            case R.id.btnWes_ARM:
                finallyResult = 5;
                break;
            case R.id.btnWes_And:
                finallyResult = 6;
                break;
            case  R.id.btnWes_WLAQ:
                finallyResult = 7;
                break;
            case R.id.btnTur_RFID:
                finallyResult = 8;
                break;
            case R.id.btnTur_QYSX:
                finallyResult = 9;
                break;
            case R.id.btnTur_VCPP:
                finallyResult = 10;
                break;
            case R.id.btnFir_And:
                finallyResult = 11;
                break;
            case R.id.btnFir_JSP:
                finallyResult = 12;
                break;
        }
        return  finallyResult;
    }
    //TODO: （待添加）根据控件ID返回对应的中间Int值
}
