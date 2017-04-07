package com.client.project;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import bean.UseView;

public class MyApplication extends Application {

    private final String TAG = this.getClass().getSimpleName();
    private static MyApplication mInstance;
    private static final byte[] sInstanceLock = new byte[0];
    private static List<Activity> activities;
    private UseView useView;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate:");
        mInstance = this;
        activities = new ArrayList<>();
    }

    public static boolean addActivity(Activity activity){
        return activities.add(activity);
    }

    public static boolean removeActivity(Activity activity){
        return activities.remove(activity);
    }

    public static MyApplication getInstance() {
        synchronized (sInstanceLock) {
            return mInstance;
        }
    }

    public UseView getUseView() {
        return useView;
    }

    public void setUseView(UseView useView) {
        this.useView = useView;
    }
}
