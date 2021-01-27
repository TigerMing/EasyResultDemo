package com.zy.ming.easyresultdemo;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * description ：
 * author : create by qiaoming on 2021/1/27
 * version :
 */
public class TestFragmentActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);
        getFragmentManager().beginTransaction().replace(R.id.fragment,new TestFragment()).commitAllowingStateLoss();
    }
}
