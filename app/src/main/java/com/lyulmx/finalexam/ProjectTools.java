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

    public static int GetKQData(Cursor cursor, SQLiteDatabase db,String ZD){
        int temp = -1;
        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){

                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                int state = cursor.getInt(cursor.getColumnIndex(ZD));
                temp = state;
            }
        }
        return temp;
    }
    public static void GetData(List<Map<String, Object>> list, Cursor cursor, SQLiteDatabase db){

        list.clear();
        int[] tx = new int[]{R.drawable.tx1,R.drawable.tx2,R.drawable.tx3,R.drawable.tx4,R.drawable.tx5,R.drawable.tx6};
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
                map.put("tx",tx[id%6]);

                list.add(map);
            }
        }
        cursor.close();
        db.close();
    }
    //TODO: (Finished!)获取该显示在ListView该显示的三种类型数据


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
    @SuppressLint("NonConstantResourceId")
    public static String getClassIdByViewid(int viewId){
        String class_id = "";
        switch (viewId){
            case R.id.btnMon_WLAQ:
                class_id = "Mon2";
                break;
            case R.id.btnMin_WLGC:
                class_id = "Mon3";
                break;
            case R.id.btnTes_ARM:
                class_id = "Tues1";
                break;
            case  R.id.btnTes_JSP:
                class_id = "Tues2";
                break;
            case R.id.btnTes_VCPP:
                class_id = "Tues3";
                break;
            case R.id.btnTes_RFID:
                class_id = "Tues4";
                break;
            case R.id.btnWes_ARM:
                class_id = "Wen3";
                break;
            case R.id.btnWes_And:
                class_id = "Wen4";
                break;
            case  R.id.btnWes_WLAQ:
                class_id = "Wen1";
                break;
            case R.id.btnTur_RFID:
                class_id = "Tur1";
                break;
            case R.id.btnTur_QYSX:
                class_id = "Tur3";
                break;
            case R.id.btnTur_VCPP:
                class_id = "Tur2";
                break;
            case R.id.btnFir_And:
                class_id = "Fir2";
                break;
            case R.id.btnFir_JSP:
                class_id = "Fir1";
                break;
        }
        return class_id;
    }
}
