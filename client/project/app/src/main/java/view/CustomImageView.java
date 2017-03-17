package view;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

/**
 * Created by YYBJ on 2017/3/14.
 */

public class CustomImageView extends View{

    private Context context;
    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = 0f,y = 0f;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(event.getX() - x) < ViewConfiguration.get(context).getScaledTouchSlop()
                        && Math.abs(event.getY() - y) <ViewConfiguration.get(context).getScaledTouchSlop()){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(event.getX() - x) < ViewConfiguration.get(context).getScaledTouchSlop()
                        && Math.abs(event.getY() - y) <ViewConfiguration.get(context).getScaledTouchSlop()){
                    Toast.makeText(context,"点击",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.dispatchGenericFocusedEvent(event);
    }

   /* @Override
    protected boolean dispatchGenericFocusedEvent(MotionEvent event) {
        float x = 0f,y = 0f;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(event.getX() - x) < ViewConfiguration.get(context).getScaledTouchSlop()
                        && Math.abs(event.getY() - y) <ViewConfiguration.get(context).getScaledTouchSlop()){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(event.getX() - x) < ViewConfiguration.get(context).getScaledTouchSlop()
                        && Math.abs(event.getY() - y) <ViewConfiguration.get(context).getScaledTouchSlop()){
                    Toast.makeText(context,"点击",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.dispatchGenericFocusedEvent(event);
    }*/
}
