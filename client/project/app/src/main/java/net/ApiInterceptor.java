package net;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络拦截器
 *
 * Created by Focux on 2016-5-26.
 */
public abstract class ApiInterceptor {

    protected static final String TAG = "ApiInterceptor";


    /**
     * 发出请求前回调
     * 可对请求做统一的处理 header修改等
     * @param request
     */
    protected Request onRequest(Request request) {
        return request;
    }

    /***
     * 请求响应后回调,可能修改响应
     * @param response
     */
    protected Response onResponseIntercept(Response response) {
        return response;
    }

    /***
     * 响应successful时回调,过滤处理响应数据
     * @param response
     * @return null 断掉响应往下继续回调，前提是采用异步回调方式访问网络，阻塞式则返回null表示响应异常
     */
    protected String onResponseFilter(String tag,String response) {
        return response;
    }

    /***
     * 请求响应后回调，不修改响应 只读取
     * 此方法会在子线程中执行
     * @param response
     */
    protected void onResponseAfter(Response response) {
    }


}
