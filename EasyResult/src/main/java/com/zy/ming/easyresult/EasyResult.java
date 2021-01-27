package com.zy.ming.easyresult;

import android.app.Activity;

import androidx.fragment.app.Fragment;

/**
 * description ：
 * author : create by qiaoming on 2021/1/14
 * version :
 */
public class EasyResult {

    public static EasyResultBuilder with(Activity activity){
        EasyResultBuilder builder = new EasyResultBuilder();
        return builder.get(activity);
    }

    public static EasyResultBuilder with(Fragment fragment){
        EasyResultBuilder builder = new EasyResultBuilder();
        return builder.get(fragment);
    }

    public static EasyResultBuilder with(android.app.Fragment fragment){
        EasyResultBuilder builder = new EasyResultBuilder();
        return builder.get(fragment);
    }
}
