package util.image;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by solo on 15/8/28.
 * QQ:1049447621
 * Version 1.1.4
 */
public class RoundTransformation implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap result = ImageUtil.getCircleBitmap(source);
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override
    public String key() {
        return "square()";
    }
}
