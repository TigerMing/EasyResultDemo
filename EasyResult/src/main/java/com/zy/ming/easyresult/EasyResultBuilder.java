package com.zy.ming.easyresult;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.fragment.app.Fragment;

import com.zy.ming.easyresult.common.ThreadType;
import com.zy.ming.easyresult.interf.IEasyResultCallback;
import com.zy.ming.easyresult.interf.IEasyResultWork;
import com.zy.ming.easyresult.support.SupportFragment;

import java.lang.ref.WeakReference;

/**
 * description ：
 * author : create by qiaoming on 2021/1/14
 * version :
 */
public class EasyResultBuilder {

    private WeakReference<Activity> mActivityRef;

    /**
     * 延迟执行
     * 只能配合work使用
     */
    private long mWorkDelay;

    private IEasyResultCallback mCallBack;

    private long mCallBackDelay;

    private ThreadType mThreadType;

    private SupportFragment mSupportFragment;
    private String mFragmentTag = "EasyResult";

    private Handler mCurHandler = new Handler(Looper.myLooper());

    public EasyResultBuilder(){
        mActivityRef = null;
    }

    public EasyResultBuilder get(Activity activity){
        mActivityRef = new WeakReference<>(activity);
        addSupportFragment();
        return this;
    }

    public EasyResultBuilder get(Fragment fragment){
        if (fragment != null){
            mActivityRef = new WeakReference<Activity>(fragment.getActivity());
        }
        addSupportFragment();
        return this;
    }

    public EasyResultBuilder get(android.app.Fragment fragment){
        if (fragment != null){
            mActivityRef = new WeakReference<Activity>(fragment.getActivity());
        }
        addSupportFragment();
        return this;
    }

    public boolean isActive(){
        if (mActivityRef != null && mActivityRef.get() != null && !mActivityRef.get().isFinishing()){
            return true;
        }
        return false;
    }

    private void addSupportFragment(){
        if (isActive()){
            FragmentManager fragmentManager = mActivityRef.get().getFragmentManager();
            android.app.Fragment fragment = fragmentManager.findFragmentByTag(mFragmentTag);
            if (fragment != null && fragment instanceof SupportFragment){
                mSupportFragment = (SupportFragment) fragment;
            }else {
                mSupportFragment = new SupportFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(mSupportFragment,mFragmentTag);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    fragmentTransaction.commitNow();
                }else {
                    fragmentTransaction.commitAllowingStateLoss();
                    fragmentManager.executePendingTransactions();
                }
            }
        }
    }

    public EasyResultBuilder workDelay(long delay){
        this.mWorkDelay = delay;
        return this;
    }

    public EasyResultBuilder callBack(IEasyResultCallback callback){
        this.mCallBack = callback;
        return this;
    }

    public EasyResultBuilder callBackDelay(long delay){
        this.mCallBackDelay = delay;
        return this;
    }

    public EasyResultBuilder threadType(ThreadType threadType){
        this.mThreadType = threadType;
        return this;
    }

    public void start(final Intent intent, final int requestCode){
        if (isActive() && mSupportFragment != null){
            mSupportFragment.setBuilder(this);
        }
        if (mWorkDelay > 0){
            mCurHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mSupportFragment != null){
                        mSupportFragment.startActivityForResult(intent, requestCode);
                    }
                }
            },mWorkDelay);
        }else {
            if (mSupportFragment != null){
                mSupportFragment.startActivityForResult(intent, requestCode);
            }
        }
    }

    /********************************************************************************/

    public ThreadType getResultThreadType(){
        return this.mThreadType;
    }

    public long getResultCallBackDelay(){
        return this.mCallBackDelay;
    }

    public IEasyResultCallback getResultCallBack(){
        return this.mCallBack;
    }
}
