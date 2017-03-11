package util;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.client.project.MyApplication;

/**
 * 在UI线程中的一些操作
 */

public class UIUtils {

    public static Handler mHandle;

    public static Context getContext(){
        return MyApplication.getInstance();
    }

    public static Handler getHandle(){
        if (mHandle == null){
            mHandle = new Handler(Looper.getMainLooper());
        }
        return mHandle;
    }

    public static boolean post(Runnable r){
        return getHandle().post(r);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandle().removeCallbacks(runnable);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    public static View inflate(int resId, ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(resId, parent, false);
    }

    public static boolean postDelayed(Runnable r,long delayed){
        return getHandle().postDelayed(r,delayed);
    }

    /**
     * Returns {@code true} if called on the main thread, {@code false} otherwise.
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /** 对toast的简易封装。线程安全，可以在非UI线程调用。 */
    public static void showToastSafe(final String str) {
        if (isOnMainThread()) {
            showToast(str);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(str);
                }
            });
        }
    }

    public static void showToast(final String str) {
        showToast(str, Toast.LENGTH_SHORT);
    }

    public static void showToast(final String str, final int duration) {
        if (isOnMainThread()) {
            Toast.makeText(getContext(), str, duration).show();
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), str, duration).show();
                }
            });
        }
    }

    public static void showLongToast(String str) {
        showToast(str, Toast.LENGTH_LONG);
    }

    /** 扩大点击区域 */
    public static void expandViewTouchDelegate(final View view, final int top,
                                               final int bottom, final int left, final int right) {
        ((View) view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);
                bounds.top -= top;
                bounds.bottom += bottom;
                bounds.left -= left;
                bounds.right += right;
                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }
    /** 恢复点击区域 */
    public static void restoreViewTouchDelegate(final View view) {
        ((View) view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                bounds.setEmpty();
                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }
    public static  void visibility(View... views){
        if (views != null&&views.length>0) {
            for (View view : views) {
                if(view.getVisibility()!=View.VISIBLE){
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public static  void invisibility(View... views){
        if (views != null&&views.length>0) {
            for (View view : views) {
                if(view.getVisibility()!=View.INVISIBLE){
                    view.setVisibility(View.INVISIBLE);
                }
            }
        }
    }
    public static void gone(View... views){
        if (views != null&&views.length>0) {
            for (View view : views) {
                if(view.getVisibility()!=View.GONE){
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

}