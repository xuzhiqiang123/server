package net;

public interface NetWorkCallBack<T> {

    boolean onStart(String tag);

    boolean onSuccess(String tag, T response);

    boolean onFailure(String tag, net.HttpException e);

    boolean onLoading(String apiName, long total, long current,
                      boolean isUploading);
}
