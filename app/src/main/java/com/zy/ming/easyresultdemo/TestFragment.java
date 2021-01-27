package com.zy.ming.easyresultdemo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zy.ming.easyresult.EasyResult;
import com.zy.ming.easyresult.common.ThreadType;
import com.zy.ming.easyresult.interf.IEasyResultCallback;

/**
 * description ：
 * author : create by qiaoming on 2021/1/27
 * version :
 */
public class TestFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test1,null);
        //普通
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyResult.with(getActivity()).callBack(new IEasyResultCallback() {
                    @Override
                    public void callBack(int requestCode, int resultCode, @Nullable Intent data) {
                        Log.d("EasyResult","requestCode = " + requestCode + "----resultCode = " + resultCode + "----data = " + data.getStringExtra("Test"));
                    }
                }).start(new Intent(getActivity(),TestActivity1.class),102);
            }
        });

        //延迟执行
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyResult.with(getActivity()).workDelay(5000).callBack(new IEasyResultCallback() {
                    @Override
                    public void callBack(int requestCode, int resultCode, @Nullable Intent data) {
                        Log.d("EasyResult","requestCode = " + requestCode + "----resultCode = " + resultCode + "----data = " + data.getStringExtra("Test"));
                    }
                }).start(new Intent(getActivity(),TestActivity1.class),202);
            }
        });

        //延迟回调 （慎用，可能导致内存泄漏）
        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyResult.with(getActivity()).callBackDelay(5000).callBack(new IEasyResultCallback() {
                    @Override
                    public void callBack(int requestCode, int resultCode, @Nullable Intent data) {
                        Log.d("EasyResult","requestCode = " + requestCode + "----resultCode = " + resultCode + "----data = " + data.getStringExtra("Test"));
                    }
                }).start(new Intent(getActivity(),TestActivity1.class),302);
            }
        });

        //不在主线程返回
        view.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("EasyResult","cur Thread : " + Thread.currentThread().getName());
                EasyResult.with(getActivity()).threadType(ThreadType.THREAD_NEW).callBack(new IEasyResultCallback() {
                    @Override
                    public void callBack(int requestCode, int resultCode, @Nullable Intent data) {
                        Log.d("EasyResult","cur Thread : " + Thread.currentThread().getName());
                        Log.d("EasyResult","requestCode = " + requestCode + "----resultCode = " + resultCode + "----data = " + data.getStringExtra("Test"));
                    }
                }).start(new Intent(getActivity(),TestActivity1.class),402);
            }
        });
        return view;
    }
}
