package com.zy.ming.easyresultdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zy.ming.easyresult.EasyResult;
import com.zy.ming.easyresult.common.ThreadType;
import com.zy.ming.easyresult.interf.IEasyResultCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //普通
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyResult.with(MainActivity.this).callBack(new IEasyResultCallback() {
                    @Override
                    public void callBack(int requestCode, int resultCode, @Nullable Intent data) {
                        Log.d("EasyResult","requestCode = " + requestCode + "----resultCode = " + resultCode + "----data = " + data.getStringExtra("Test"));
                    }
                }).start(new Intent(MainActivity.this,TestActivity1.class),102);
            }
        });

        //延迟执行
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyResult.with(MainActivity.this).workDelay(5000).callBack(new IEasyResultCallback() {
                    @Override
                    public void callBack(int requestCode, int resultCode, @Nullable Intent data) {
                        Log.d("EasyResult","requestCode = " + requestCode + "----resultCode = " + resultCode + "----data = " + data.getStringExtra("Test"));
                    }
                }).start(new Intent(MainActivity.this,TestActivity1.class),202);
            }
        });

        //延迟回调 （慎用，可能导致内存泄漏）
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyResult.with(MainActivity.this).callBackDelay(5000).callBack(new IEasyResultCallback() {
                    @Override
                    public void callBack(int requestCode, int resultCode, @Nullable Intent data) {
                        Log.d("EasyResult","requestCode = " + requestCode + "----resultCode = " + resultCode + "----data = " + data.getStringExtra("Test"));
                    }
                }).start(new Intent(MainActivity.this,TestActivity1.class),302);
            }
        });

        //不在主线程返回
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("EasyResult","cur Thread : " + Thread.currentThread().getName());
                EasyResult.with(MainActivity.this).threadType(ThreadType.THREAD_NEW).callBack(new IEasyResultCallback() {
                    @Override
                    public void callBack(int requestCode, int resultCode, @Nullable Intent data) {
                        Log.d("EasyResult","cur Thread : " + Thread.currentThread().getName());
                        Log.d("EasyResult","requestCode = " + requestCode + "----resultCode = " + resultCode + "----data = " + data.getStringExtra("Test"));
                    }
                }).start(new Intent(MainActivity.this,TestActivity1.class),402);
            }
        });

        //不在主线程返回
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TestFragmentActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}