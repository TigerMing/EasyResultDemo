package com.zy.ming.easyresult.interf;

import android.content.Intent;

import androidx.annotation.Nullable;

/**
 * description ：
 * author : create by qiaoming on 2021/1/14
 * version :
 */
public interface IEasyResultCallback {
    void callBack(int requestCode, int resultCode, @Nullable Intent data);
}
