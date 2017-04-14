package com.client.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
    private Fragment currentFragment;

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
        setSelect(0);
        initSlidingFragment();
        mPresenter = new HomePresenter();
    }

    private void initSlidingFragment() {
        SlidingFragment slidingFragment = new SlidingFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.left_sliding_layout,slidingFragment).commit();
    }

    private void setSelect(int select){
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        switch (select){
            case 0:
                if (homeFragment == null){
                    homeFragment = new HomeFragment();
                }
                if (currentFragment != null){
                    transition.hide(currentFragment);
                }
                if (homeFragment.isAdded()){
                    transition.show(homeFragment);
                }else {
                    transition.add(R.id.home_content,homeFragment,"home");
                }
                transition.commit();
                currentFragment = homeFragment;
                break;
        }
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
