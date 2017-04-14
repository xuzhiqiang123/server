package util.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.client.project.R;

import util.UIUtils;


/**
 * 圆角 圆边
 */
public class TopRoundTransform implements com.squareup.picasso.Transformation {



    int width;
    int height;
    @Override
    public Bitmap transform(Bitmap source) {
        // 其原理就是：先建立一个与图片大小相同的透明的Bitmap画板
        // 然后在画板上画出一个想要的形状的区域。
        // 最后把源图片帖上。
         width = source.getWidth();
         height = source.getHeight();
        Bitmap paintingBoard = Bitmap.createBitmap(width, height,  Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(paintingBoard);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        Bitmap shape2 = BitmapFactory.decodeResource(UIUtils.getContext().getResources(), R.drawable.dynamic_pic_bg);
        Bitmap scaledShape2 = Bitmap.createScaledBitmap(shape2, width, height, true);
        canvas.drawBitmap(scaledShape2, 0, 0, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Bitmap scaledShape = Bitmap.createScaledBitmap(source, width, height, true);
        canvas.drawBitmap(scaledShape, 0, 0, paint);
        source.recycle();
        return paintingBoard;
    }

    @Override
    public String key() {
        return "xdsw"+width+height;
    }
}