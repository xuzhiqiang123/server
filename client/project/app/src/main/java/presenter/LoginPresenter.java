package presenter;

import android.util.Log;

import net.ApiClient;
import net.Presenter;

import java.util.HashMap;

import bean.UseView;
import response.BaseResponse;
import response.LoginResponse;
import view.IView.ILoginView;

/**
 * Created by YYBJ on 2017/4/7.
 */

public class LoginPresenter extends Presenter {

    public static final String LOGIN = "/project1/servlets/Login";
    public static final String TAG = "LoginPresenter";

    private ILoginView mView;

    public LoginPresenter(ILoginView mView) {
        this.mView = mView;
    }

    public void login(UseView useView) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", useView.id);
        params.put("platform", useView.platform);
        params.put("nickname", useView.nickname);
        params.put("gender", useView.gender);
        params.put("province", useView.province);
        params.put("city", useView.city);
        params.put("useIcon", useView.useIcon);
        params.put("auth", useView.auth);
        ApiClient.post(LOGIN, params, LoginResponse.class, this);
    }

    @Override
    public boolean onSuccess(String tag, BaseResponse response) {
        if (super.onSuccess(tag, response)) {
            if (tag.equals(LOGIN) && response instanceof LoginResponse) {
                LoginResponse bean = (LoginResponse) response;
                Log.e(TAG, response.toString());
                if (bean.getStatus() == 1) {
                    mView.loginSucceed(bean);
                }
            }
        }
        return super.onSuccess(tag, response);
    }
}
