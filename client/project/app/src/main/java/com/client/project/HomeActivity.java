package com.client.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.client.project.databinding.ActivityHomeBinding;

import fragment.HomeFragment;
import fragment.SlidingFragment;
import presenter.HomePresenter;
import util.BroadCastUtil;

import static util.BroadCastUtil.HOME_ACTIVITY_BROADCAST_ACTION;

public class HomeActivity extends BaseActivity implements OnClickListener{

    private ActivityHomeBinding mBinding;
    private HomePresenter mPresenter;
    private SlidingFragment slidingFragment;
    private HomeFragment homeFragment;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int type = intent.getIntExtra("type",-1);
            switch (type){
                case BroadCastUtil.HOME_BROADCAST_DATA_TEST:
                    break;
                case BroadCastUtil.HOME_BROADCAST_IM_CONNECT:
                    break;
                case BroadCastUtil.HOME_BROADCAST_IM_DISCONNECT:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityHomeBinding) setContentBinding(this,R.layout.activity_home);
        IntentFilter filter = new IntentFilter(HOME_ACTIVITY_BROADCAST_ACTION);
        registerReceiver(mReceiver,filter);
        init();
        mPresenter = new HomePresenter();
    }

    private void init() {
        slidingFragment = new SlidingFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.left_sliding_layout,slidingFragment).commit();
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_content,homeFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    @Override
    public void finish() {
        moveTaskToBack(true);
    }
}
