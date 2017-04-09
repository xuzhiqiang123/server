package view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import util.UIUtils;

/**
 * Created by YYBJ on 2017/4/9.
 */

public class LeftSlidingView extends FrameLayout {

    public LeftSlidingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = UIUtils.getScreenWidth() / 3;
        setMeasuredDimension(width, MeasureSpec.getSize(heightMeasureSpec));
    }
}
