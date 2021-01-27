package com.zy.ming.easyresult.support;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.zy.ming.easyresult.EasyResultBuilder;
import com.zy.ming.easyresult.common.ThreadPoolByFIFOQueue;
import com.zy.ming.easyresult.common.ThreadType;
import com.zy.ming.easyresult.interf.IEasyResultCallback;

/**
 * description ：
 * author : create by qiaoming on 2021/1/14
 * version :
 */
public class SupportFragment extends Fragment {

    private EasyResultBuilder mBuilder;

    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    public void setBuilder(EasyResultBuilder builder){
        this.mBuilder = builder;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (mBuilder != null && mBuilder.isActive()){
            if (mBuilder.getResultCallBack() != null){
                mMainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mBuilder.getResultCallBack() == null){
                            return;
                        }
                        if (mBuilder.getResultThreadType() == ThreadType.THREAD_MAIN){
                            mBuilder.getResultCallBack().callBack(requestCode, resultCode, data);
                        }else {
                            ThreadPoolByFIFOQueue.getExecutor().execute(getCallBackRunnable(mBuilder.getResultCallBack(),requestCode, resultCode, data));
                        }
                    }
                },mBuilder.getResultCallBackDelay());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Runnable getCallBackRunnable(final IEasyResultCallback callback, final int requestCode, final int resultCode, final Intent data){
        return new Runnable() {
            @Override
            public void run() {
                if (callback != null){
                    callback.callBack(requestCode, resultCode, data);
                }
            }
        };
    }
}
