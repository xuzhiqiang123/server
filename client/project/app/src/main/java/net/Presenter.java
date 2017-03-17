package net;

import response.BaseResponse;
import util.UIUtils;

/**
 * Created by YYBJ on 2017/3/14.
 */

public class Presenter implements NetWorkCallBack<BaseResponse>{

    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public boolean onStart(String tag) {
        return false;
    }

    @Override
    public boolean onSuccess(String tag, BaseResponse response) {
        if (response == null) {//防止返回数据格式不正确
            UIUtils.showToast("服务异常");
            return false;
        }
        return true;
    }

    @Override
    public boolean onFailure(String tag, net.HttpException e) {
        return false;
    }

    @Override
    public boolean onLoading(String apiName, long total, long current, boolean isUploading) {
        return false;
    }
}
