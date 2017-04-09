package view;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by YYBJ on 2017/4/5.
 */

public class SlidingMenu extends SlidingPaneLayout {

    private int slideWidth;

    public SlidingMenu(Context context) {
        super(context);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        slideWidth = MeasureSpec.getSize(widthMeasureSpec) / 8;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x = Math.abs((int) ev.getX() -getLeft());
                if (x > slideWidth && !isOpen()){
                    return false;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
