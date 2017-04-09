package com.client.project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

import bean.UseView;
import login.LoginApi;
import login.OnLoginListener;
import login.UserInfo;
import presenter.LoginPresenter;
import response.LoginResponse;
import util.IMConnect;
import util.SharePreferenceUtil;
import util.StringUtils;
import util.UIUtils;
import view.IView.ILoginView;

/**
 * Created by YYBJ on 2017/4/6.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    private ImageView qqLogin;
    private ImageView weixinLogin;
    private RelativeLayout loginLayout;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        initView();
        mPresenter = new LoginPresenter(this);
    }

    private void initView() {
        qqLogin = (ImageView) findViewById(R.id.qq_login);
        weixinLogin = (ImageView) findViewById(R.id.weixin_login);
        loginLayout = (RelativeLayout) findViewById(R.id.login_layout);
        initEvent();
        handleData();
    }

    private void handleData() {
        final boolean first = SharePreferenceUtil.getBoolean(SharePreferenceUtil.FIRST_LOGIN, false);
        if (first) {
            loginLayout.setVisibility(View.GONE);
            String useView = SharePreferenceUtil.getString(SharePreferenceUtil.LOGIN_USE_VIEW, "");
            if (!StringUtils.isEmpty(useView)) {
                UseView use = JSON.parseObject(useView, UseView.class);
                MyApplication.getInstance().setUseView(use);
                IMConnect.startIMService();
                UIUtils.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startHomeActivity();
                        finish();
                    }
                }, 2000);
            }
        } else {
            loginLayout.setVisibility(View.VISIBLE);
        }
    }

    /* 获取平台列表,显示平台按钮*/
    private void initEvent() {
        qqLogin.setOnClickListener(this);
        weixinLogin.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qq_login:
                login("QQ");
                break;
            case R.id.weixin_login:
                login("Wechat");
                break;
        }
    }

    private void login(String platformName) {
        LoginApi api = new LoginApi();
        //设置登陆的平台后执行登陆的方法
        api.setPlatform(platformName);
        api.setOnLoginListener(new OnLoginListener() {
            public boolean onLogin(String platform, HashMap<String, Object> res) {
                UseView useView = new UseView();
                useView.id = (String) res.get("userId");
                useView.auth = 3;
                useView.city = (String) res.get("city");
                useView.province = (String) res.get("province");
                useView.useIcon = (String) res.get("figureurl_2");
                useView.gender = (String) res.get("gender");
                useView.platform = platform;
                useView.nickname = (String) res.get("nickname");
                Log.e("onLogin",res.toString());
                Log.e("useView",useView.toString());
                mPresenter.login(useView);
                return true;
            }

            public boolean onRegister(UserInfo info) {
                // 填写处理注册信息的代码，返回true表示数据合法，注册页面可以关闭
                return true;
            }
        });
        api.login(this);
    }

    @Override
    public void loginSucceed(LoginResponse bean) {
        MyApplication.getInstance().setUseView(bean.useView);
        SharePreferenceUtil.saveBoolean(SharePreferenceUtil.FIRST_LOGIN, true);
        SharePreferenceUtil.saveString(SharePreferenceUtil.LOGIN_USE_VIEW, JSON.toJSONString(bean.useView));
        startHomeActivity();
        IMConnect.startIMService();
        finish();
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
