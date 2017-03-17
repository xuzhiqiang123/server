package net;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.LogUtil;
import util.ThreadManager;
import util.UIUtils;

/**
 * Created by YYBJ on 2017/3/14.
 */

public class OkHttp3ClientImp {


    private static final String TAG = "OkHttp3ClientImp";
    /**
     * 拦截监听
     */
    private static Map<String, ApiInterceptor> interceptorsAsync = new ConcurrentHashMap<>();
    /**
     * 阻塞拦截
     */
    private static Map<String, ApiInterceptor> interceptors = new ConcurrentHashMap<>();

    private static final long DEFAULT_WRITE_TIMEOUT = 60;
    private static final long DEFAULT_READ_TIMEOUT = 60;
    private static final long DEFAULT_CONN_TIMEOUT = 30;
    private static final OkHttpClient mClient = new OkHttpClient.Builder().
            writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS).
            readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS).
            connectTimeout(DEFAULT_CONN_TIMEOUT, TimeUnit.SECONDS).
            addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    // request
                    onRequestInterceptAsync(request);

                    Response response = chain.proceed(request);
                    //可能修改响应数据
                    //response = onResponseIntercept(response);

                    onResponseInterceptAsync(response);

                    return response;
                }
            }).build();
    private static OkHttp3ClientImp mInstance;
    private static byte[] mInstanceLock = new byte[0];
    private OkHttp3ClientImp(){
    }

    public static OkHttp3ClientImp getInstance(){
        if (mInstance == null){
            synchronized (mInstanceLock){
                if (mInstance == null){
                    mInstance = new OkHttp3ClientImp();
                }
            }
        }
        return mInstance;
    }

    private static void onRequestInterceptAsync(final Request request) {
        ThreadManager.getLongPool().execute(new Runnable() {
            @Override
            public void run() {
                for (Map.Entry<String, ApiInterceptor> entry : interceptorsAsync.entrySet()) {
                    entry.getValue().onRequest(request);
                }
            }
        });
    }

    private static void onResponseInterceptAsync(final Response response) {
        //Response response1 = response.newBuilder()
        ThreadManager.getLongPool().execute(new Runnable() {
            @Override
            public void run() {
                for (Map.Entry<String, ApiInterceptor> entry : interceptorsAsync.entrySet()) {
                    entry.getValue().onResponseAfter(response);
                }
            }
        });
    }

    public void getInbackgroundGet(String url, final String tag, final NetWorkCallBack<String> callback) {
        callback.onStart(tag);

        Request req = new Request.Builder()
                .get()
                .url(url)
                .tag(tag)
                .build();
        executeAsync(req, new StringCallbackImpl(callback, tag));
    }

    public static void executeAsync(Request request, Callback callback) {
        if (request == null) {
            if (callback != null) {
                callback.onFailure(null,new IOException("请求内容为空"));
            }
            return;
        }
        mClient.newCall(request).enqueue(callback);
    }

    public void postBodyInbackground(String url, final String tag, String body, final NetWorkCallBack<String> callback) {
        callback.onStart(tag);

        Request req = buildPostJsonRequest(url, body, tag,false);
        executeAsync(req, new StringCallbackImpl(callback, tag));
    }

    private static Request buildPostJsonRequest(String url, String postBody, String tag,boolean isGZIP) {
        RequestBody requestBody = null;
        isGZIP = false;
        if (isGZIP) {
            byte[] body = HttpUtil.gzip(postBody);
            if (body == null) {
                LogUtil.e(TAG, "buildPostJsonRequest: 请求gzip后 body为空 源body："+postBody);
                return null;
            }
            // test ONly

            //test(body);
            LogUtil.e(TAG, "buildPostJsonRequest: gzip后 body 长度："+body.length);
            requestBody = RequestBody.create(MediaType.parse("application/json"), body);
        }else {
            requestBody =RequestBody.create(MediaType.parse("application/json"), postBody);
        }
        //test(postBody);
        Request.Builder builder = new Request.Builder()
                .post(requestBody)
                .url(url)
                .tag(tag);

        return builder.build();
    }

    class StringCallbackImpl implements Callback {

        NetWorkCallBack<String> callback;
        String tag;

        public StringCallbackImpl(NetWorkCallBack<String> requestCallback, String tag) {
            this.callback = requestCallback;
            this.tag = tag;
        }


        void failure(final net.HttpException e) {
            if (callback != null) {
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(tag, e);
                    }
                });
            }
        }

        @Override
        public void onFailure(Call call, IOException e) {
            failure(new net.HttpException(e));
        }

        @Override
        public void onResponse(Call call, Response response) {
            LogUtil.i(TAG, "API:" + tag + " onResponse ：" + response);

            boolean successful = false;
            try {
                if (response.isSuccessful()) {
                    final String result = response.body().string();//string()只能调用一次，否则发生java.lang.IllegalStateException: closed
                    LogUtil.i(TAG, "API:" + tag + " onResponse  ---body>> " + result);
                    final String filter = onResponseFilter(response.request().tag().toString(),result);
                    //filter == null 请求被断掉
                    if (filter!=null && callback != null) {
                        UIUtils.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess(tag, filter);
                            }
                        });
                    }
                    successful = true;
                }

                if (!successful) {
                    failure(new net.HttpException(response.code()));
                }
            } catch (Exception e) {
                LogUtil.e(e);
                failure(new net.HttpException(e));
            } finally {
                try {
                    response.body().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String onResponseFilter(String tag,String response) {
        for (Map.Entry<String, ApiInterceptor> entry : interceptors.entrySet()) {
            response = entry.getValue().onResponseFilter(tag,response);
        }
        return response;
    }
}
