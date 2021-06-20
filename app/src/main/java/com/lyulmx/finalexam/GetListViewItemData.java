package com.lyulmx.finalexam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetListViewItemData {

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

    public static void GetAllDataFromDB(Cursor cursor, SQLiteDatabase db,int itemId) {

        cursor = db.rawQuery("select * from student where id=?", new String[]{String.valueOf(itemId)});
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
        }
    }
}
