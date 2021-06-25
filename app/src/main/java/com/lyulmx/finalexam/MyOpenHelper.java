package com.lyulmx.finalexam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " +
                "users(_id integer primary key autoincrement,Stuname varchar(10), " +
                "Stuphone varchar(11), StuId varchar(12), StuApartment varchar(6), StuSex int(1))");

        db.execSQL("create table if not exists KQ(_id integer primary key autoincrement,Stuname varchar(10)," +
                "Mon2 int(10) ,Mon3 int(10),Tues1 int(10),Tues2 int(10),Tues3 int(10), Tues4 int(10), Wen1 int(10), Wen3 int(10), Wen4 int(10)," +
                "Tur1 int(10), Tur2 int(10), Tur3 int(10), Fir1 int(10), Fir2 int(10))");
    }
    //TODO: (Finished!)创建数据表

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
