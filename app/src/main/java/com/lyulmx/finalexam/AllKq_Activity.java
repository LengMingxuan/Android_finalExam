package com.lyulmx.finalexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AllKq_Activity extends AppCompatActivity {
    SQLiteDatabase db;
    Button btnFrKqBackAduToListview;
    Cursor cursor;
    TextView tvStuKQ;
    int strId;
    String finalTvString = "";
    MyOpenHelper helper = new MyOpenHelper(this);
    String[] AllClassWeek = new String[]{"Mon2","Mon3","Tues1","Tues2","Tues3","Tues4","Wen1",
            "Wen3","Wen4","Tur1","Tur2","Tur3","Fir1","Fir2"};
    String[] StuName = new String[]{"冷鸣轩","张三"};

    int[] WeekClassState = new int[15];
    int ii;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_kq);
        String[] AllClass = getResources().getStringArray(R.array.AllClass);

        tvStuKQ = findViewById(R.id.tvStuKQ);
        btnFrKqBackAduToListview = findViewById(R.id.btnFrKqBackAduToListview);

        btnFrKqBackAduToListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllKq_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        db = helper.getReadableDatabase();
        for (int i = 1; i <= 2; i++) {
            cursor = db.rawQuery("select * from KQ where _id=?", new String[]{String.valueOf(i)});
            finalTvString += StuName[i-1] + ":<br>";
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    for (int j = 0; j < 14; j++){
                        ii = cursor.getInt(cursor.getColumnIndex(AllClassWeek[j]));
                         if (ii == 2){
                            finalTvString += AllClassWeek[j]+"-"+AllClass[j]+"("+ "<span style=\"background-color:#FFEB3B;\">上课迟到</span>" + ")" +"<br>";
                         }
                         else if (ii == 3){
                             finalTvString += AllClassWeek[j]+"-"+AllClass[j]+
                                     "("+ "<span style=\"background-color:#E91E63;\"><font color=\"#ffffff\">课程缺勤</font></span>" + ")" +" <br>";
                         }
//                        else if (ii == 1){
//                            finalTvString += "正常出勤" + " ";
//                        }
//                        else {
//                            finalTvString += "正常出勤" + " ";
//                        }
                    }
                    finalTvString += "\n";
                }
            }
        }
        tvStuKQ.setText(HtmlCompat.fromHtml(finalTvString, Html.FROM_HTML_MODE_COMPACT));
    }
}