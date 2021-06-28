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
                btnQckIN.setText(msg.arg2+""+" | 跳过");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firing);

        btnQckIN = findViewById(R.id.btnQuickIn);
        //TODO: (Finished!)链接控件

        Thread myThread = new Thread() {//创建子线程
            @Override
            public void run() {
                int tickets = 1;
                for(int i = 0; i<2; i++){
                    try {
                        Thread.sleep(1000);
                        if(tickets > 0) {
                            Message message= new Message();
                            message.arg2 = tickets--;
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
        //TODO: (Finished!)定义并启动线程显示倒计时按钮上的Text

        //背景图片
        this.getWindow().setBackgroundDrawableResource(R.drawable._back);
        //TODO: (Finished!)设置启动界面的背景图片

        btnQckIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notOnClickFlag = false;
                Intent intent = new Intent(firing_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //TODO: (Finished!)快速进入按钮 实现
    }
}