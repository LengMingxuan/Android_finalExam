package com.lyulmx.finalexam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;

public class DetailButtonSetColor  {
    SQLiteDatabase db;
    static int ii;



    public static void setColor(SQLiteDatabase db, Cursor cursor,MyOpenHelper helper, Button button,int j,int buttonid) {
        db = helper.getReadableDatabase();

    }
}
