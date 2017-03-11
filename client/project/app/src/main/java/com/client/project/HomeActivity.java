package com.client.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.client.project.databinding.ActivityHomeBinding;

import service.IMService;
import util.PollingUtil;
import util.UIUtils;

public class HomeActivity extends BaseActivity implements OnClickListener{

    private ActivityHomeBinding mBinding;
    public static final String HOME_ACTIVITY_BROADCAST_ACTION = "home.activity.broadcast.action";
    private int count;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            count++;
            mBinding.homeStart1.setText(count+"");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityHomeBinding) setContentBinding(this,R.layout.activity_home);
        IntentFilter filter = new IntentFilter(HOME_ACTIVITY_BROADCAST_ACTION);
        registerReceiver(mReceiver,filter);
        initEvent();
    }

    private void initEvent() {
        mBinding.homeStart.setOnClickListener(this);
        mBinding.homeStart1.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_start:
//                IMConnect.startIMService();
                PollingUtil.startPollingService(UIUtils.getContext(), 1 * 1000, IMService.class, "IMServer");
                break;
            case R.id.home_start1:
//                IMConnect.startIMService();
                Intent intent = new Intent(this,IMService.class);
                startService(intent);
                break;
        }
    }
}
