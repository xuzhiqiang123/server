package net;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import java.util.Map;

import util.LogUtil;
import util.UIUtils;

/**
 * Created by YYBJ on 2017/3/14.
 */

public class ApiClient {

    private static final String TAG = "ApiClient";
    public static String host = "http://192.168.121.115:8080";
    public static String account;

    public static void init(String host,String mCurrentUserId){
        ApiClient.host = host;
        account = mCurrentUserId;
    }

    public synchronized static void get(String api,Class responseClass,NetWorkCallBack callback){
        //1.判断是否有网络
        if (checkConnect(api, callback)) return;
        //2.构建URL
        String reqestUrl = createURL(host, api, account);
        LogUtil.i(TAG, "create url is  " + reqestUrl);
        OkHttp3ClientImp.getInstance().getInbackgroundGet(reqestUrl,api,new JsonCallBack(callback,responseClass));
    }

    public synchronized static void post(final String api, Map<String, Object> params, final Class responseClass, final NetWorkCallBack callback) {
        try {
            //1.判断是否有网络
            if (checkConnect(api, callback)) return;

            //2.构建URL
            final String reqestUrl = createURL(host, api, account);
            LogUtil.i(TAG, "API:" + api + " create url is  " + reqestUrl);
            //3.构建body
            String requestBody = createRequestBody(params);
            LogUtil.i(TAG, api + " >>the post request body is " + requestBody);

            OkHttp3ClientImp.getInstance().postBodyInbackground(reqestUrl, api, requestBody, new JsonCallBack(callback, responseClass));
        } catch (Exception e) {
            LogUtil.e(TAG, "网络错误", e);
            if (callback != null) {
                callback.onFailure(TAG, new HttpException(e));
            }
        }
    }

    public static String createRequestBody(Map<String, Object> params) {
        String jsonParams = null;
        if (params != null) {
            jsonParams = JSON.toJSONString(params);
        }
        return jsonParams;
    }

    public static String createURL(String host, String api, String account) {
        if (!TextUtils.isEmpty(api)) {

            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(host);//主机地址
            urlBuilder.append(api);//接口名称
            //添加Outh
            urlBuilder = addAccount(urlBuilder,account);
            return urlBuilder.toString();
        }
        return null;
    }

    private static StringBuilder addAccount(StringBuilder urlBuilder,String account) {
        appendWithCheck(urlBuilder);
        urlBuilder.append("account=");
        urlBuilder.append(account);
        return urlBuilder;
    }

    private static void appendWithCheck(StringBuilder urlBuilder) {
        if (urlBuilder.toString().indexOf("?") == -1) {
            urlBuilder.append("?");
        } else {
            urlBuilder.append("&");
        }
    }

    private static boolean checkConnect(final String api, final NetWorkCallBack callback) {
        //1.判断是否有网络
        if (!HttpUtil.isConnected(UIUtils.getContext())) {
            LogUtil.i(TAG, "no internet !!");
            callback.onFailure(api,new net.HttpException("网络断开"));
            return true;
        }
        return false;
    }
}
