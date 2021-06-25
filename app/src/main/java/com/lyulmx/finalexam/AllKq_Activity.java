package com.lyulmx.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class AllKq_Activity extends AppCompatActivity {
    SQLiteDatabase db;
    Cursor cursor;
    TextView tvStuKQ;
    int strId;
    String finalTvString = "";
    MyOpenHelper helper = new MyOpenHelper(this);
    String[] AllClassWeek = new String[]{"Mon2","Mon3","Tues1","Tues2","Tues3","Tues4","Wen1",
            "Wen3","Wen4","Tur1","Tur2","Tur3","Fir1","Fir2"};
    int[] WeekClassState = new int[15];
    int ii;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_kq);

        tvStuKQ = findViewById(R.id.tvStuKQ);

        db = helper.getReadableDatabase();
        for (int i = 1; i <= 2; i++) {
            cursor = db.rawQuery("select * from KQ where _id=?", new String[]{String.valueOf(i)});
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    for (int j = 0; j < 14; j++){
                        ii = cursor.getInt(cursor.getColumnIndex(AllClassWeek[j]));
                        if (ii == 1){
                            finalTvString += "正常出勤" + " ";
                        }
                        else if (ii == 2){
                            finalTvString += "上课迟到" + " ";
                        }
                        else if (ii == 3){
                            finalTvString += "课程缺勤" + " ";
                        }
                        else {
                            finalTvString += "正常出勤" + " ";
                        }
                    }
                    finalTvString += "\n";
                }
            }
        }
        tvStuKQ.setText(finalTvString);
    }
}