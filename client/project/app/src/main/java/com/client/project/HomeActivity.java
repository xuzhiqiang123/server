package com.client.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.client.project.databinding.ActivityHomeBinding;

import presenter.HomePresenter;
import util.BroadCastUtil;
import util.IMConnect;
import util.UIUtils;

import static util.BroadCastUtil.HOME_ACTIVITY_BROADCAST_ACTION;

public class HomeActivity extends BaseActivity implements OnClickListener{

    private ActivityHomeBinding mBinding;
    private HomePresenter mPresenter;

    private int count;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int type = intent.getIntExtra("type",-1);
            switch (type){
                case BroadCastUtil.HOME_BROADCAST_DATA_TEST:
                    mBinding.connect.setVisibility(View.GONE);
                    break;
                case BroadCastUtil.HOME_BROADCAST_IM_CONNECT:
                    mBinding.connect.setVisibility(View.VISIBLE);
                    break;
                case BroadCastUtil.HOME_BROADCAST_IM_DISCONNECT:

                    break;
            }
            count++;
            Log.e("interal",count+"");
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
        mPresenter = new HomePresenter();
    }

    private void initEvent() {
        mBinding.homeStart.setOnClickListener(this);
        mBinding.homeStart1.setOnClickListener(this);
        mBinding.image.setOnTouchListener(new View.OnTouchListener() {
            float x = 0,y = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                        Log.e("down","x = "+x+"y = "+y);
                        break;
                    /*case MotionEvent.ACTION_MOVE:
                        if (Math.abs(event.getX() - x) <= ViewConfiguration.get(UIUtils.getContext()).getScaledTouchSlop()
                                && Math.abs(event.getY() - y) <= ViewConfiguration.get(UIUtils.getContext()).getScaledTouchSlop()){
                            mBinding.image.getParent().requestDisallowInterceptTouchEvent(true);
                        }else {
                            mBinding.image.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        Log.e("move","x = "+event.getX()+"y = "+event.getY());
                        break;*/
                    case MotionEvent.ACTION_UP:
                        Log.e("up","x = "+event.getX()+"y = "+event.getY()+","+Math.abs(event.getX() - x));
                        if (Math.abs(event.getX() - x) <= ViewConfiguration.get(UIUtils.getContext()).getScaledTouchSlop()
                                && Math.abs(event.getY() - y) <= ViewConfiguration.get(UIUtils.getContext()).getScaledTouchSlop()){
                            Toast.makeText(HomeActivity.this,"点击",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return HomeActivity.super.dispatchTouchEvent(event);
            }
        });
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
                IMConnect.startIMService();
                break;
            case R.id.home_start1:
//                mPresenter.testGet();
                mPresenter.testPost();
                break;
        }
    }
}
