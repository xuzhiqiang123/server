package view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import util.UIUtils;

/**
 * Created by YYBJ on 2017/4/9.
 */

public class LeftSlidingView extends LinearLayout {

    private Context context;
    private ImageView portrait;
    private TextView nickname;

    public LeftSlidingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int width = UIUtils.getScreenWidth() / 3;
        setMeasuredDimension(width, MeasureSpec.getSize(heightMeasureSpec));
    }
}
