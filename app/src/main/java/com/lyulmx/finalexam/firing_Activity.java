package com.lyulmx.finalexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

public class firing_Activity extends AppCompatActivity {
    boolean notOnClickFlag = true;
    Button btnQckIN;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1){
                btnQckIN.setText(msg.obj+""+" | 跳过");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //背景图片
        this.getWindow().setBackgroundDrawableResource(R.drawable._back);

        setContentView(R.layout.activity_firing);
        btnQckIN = findViewById(R.id.btnQuickIn);
        Thread myThread = new Thread() {//创建子线程
            @Override
            public void run() {
                int tickets = 3;
                for(int i = 0; i<=3; i++){
                    try {
                        Thread.sleep(1000);
                        if(tickets > 0) {
                            Message message= new Message();
                            message.arg1 = 1;
                            message.obj = tickets--;
                            handler.sendMessage(message);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (notOnClickFlag) {
                    Intent it = new Intent(firing_Activity.this, MainActivity.class);
                    startActivity(it);
                    finish();//关闭当前活动
                }
            }
        };
        myThread.start();//启动线程
        btnQckIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notOnClickFlag = false;
                Intent intent = new Intent(firing_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}