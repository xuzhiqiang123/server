package util.image;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

import util.UIUtils;

/**
 * Created by solo on 15/8/28.
 * QQ:1049447621
 * Version 1.1.4
 */
public class RoundCornerTransformation implements Transformation {
    private float roundPx = UIUtils.dip2px(5);
    public RoundCornerTransformation(){

    }
    public RoundCornerTransformation(int angle) {
        roundPx = UIUtils.dip2px(angle);
    }
    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap result = ImageUtil.convertToRoundedCorner(source,roundPx);
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override
    public String key() {
        return "convertToRoundedCorner()";
    }
}
