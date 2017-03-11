package com.client.project;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by YYBJ on 2017/3/11.
 */

public class BaseActivity extends FragmentActivity {

    protected final String TAG = this.getClass().getSimpleName();

    public ViewDataBinding setContentBinding(Activity context, int layoutId){
        return DataBindingUtil.setContentView(context,layoutId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        MyApplication.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.removeActivity(this);
        Log.i(TAG, "onDestroy: ");
    }
}
