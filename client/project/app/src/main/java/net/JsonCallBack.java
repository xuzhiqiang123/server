package net;

import com.alibaba.fastjson.JSON;

import util.LogUtil;
import util.StringUtils;

/**
 * 统一业务回调，把json数据解析为对应对象
 * Created by Focux on 2016-5-19
 */
public class JsonCallBack implements NetWorkCallBack<String> {
    private static final String TAG = "JsonCallBack";
    NetWorkCallBack callback;
    Class responseClass;

    public JsonCallBack(NetWorkCallBack callback, Class responseClass) {
        this.callback = callback;
        this.responseClass = responseClass;
    }

    @Override
    public boolean onStart(String tag) {
        if (callback != null) {
            return callback.onStart(tag);
        }
        return false;
    }

    @Override
    public boolean onSuccess(String tag, String response) {
        try {
            if (StringUtils.isEmpty(response)) {
                return onFailure(tag, new net.HttpException("响应内容为空"));
            }
            if (callback != null) {
                callback.onSuccess(tag, JSON.parseObject(response, responseClass));
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "API:"+tag+" Exception onSuccess response >>>>  "+response);
            onFailure(tag, new net.HttpException(e));
        }
        return false;
    }

    @Override
    public boolean onFailure(String tag, net.HttpException e) {
        LogUtil.e(TAG, "onFailure API:" + tag + " ," + e);
        if (callback != null) {
            return callback.onFailure(tag, e);
        }
        return false;
    }

    @Override
    public boolean onLoading(String apiName, long total, long current, boolean isUploading) {
        return false;
    }
}