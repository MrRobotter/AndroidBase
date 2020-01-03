package com.joinyon.androidbase.Object;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.joinyon.androidbase.R;

/**
 * Handler机制中最重要的四个对象
 * <p>
 * Handler：负责发送消息及处理消息
 * Looper：复制不断的从消息队列中取出消息，并且给发送本条消息的Handler
 * MessageQueue：负责存储消息
 * Message:消息本身，负责携带数据
 */
public class HandlerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        new Thread() {
            @Override
            public void run() {
                super.run();
                mainHandler.sendEmptyMessage(0);
                Log.e("TAG", "Thread=" + Thread.currentThread().getName() + ",发送消息");

            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                super.run();
                childHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };
            }
        }.start();
    }


    /**
     * 在主线程中初始化Handler,接收从子线程发过来的消息
     * 从子线程将消息发到主线程
     */
    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("TAG", "Thread=" + Thread.currentThread().getName() + ",收到从子线程返回的消息=" + msg.what);
        }
    };


    private Handler childHandler;


    public void sendToChild(View view) {
        //childHandler.sendEmptyMessage(1);
    }
}
