package util.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.client.project.R;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

import util.UIUtils;

public class TopRoundBitmapDisplayer implements BitmapDisplayer {

    public TopRoundBitmapDisplayer() {
    }

    @Override
    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        imageAware.setImageBitmap(BitmapFillet.fillet(bitmap, UIUtils.dip2px(10),bitmap.getWidth(),bitmap.getHeight()));

    }

    public static Bitmap fillet(Bitmap bitmap, int width, int height) {
        try {
            // 其原理就是：先建立一个与图片大小相同的透明的Bitmap画板
            // 然后在画板上画出一个想要的形状的区域。
            // 最后把源图片帖上。
            Bitmap.Config config = Bitmap.Config.ARGB_8888;
            Bitmap paintingBoard = Bitmap.createBitmap(width, height, config);
            Canvas canvas = new Canvas(paintingBoard);

            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

            Bitmap shape2 = BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.dynamic_pic_bg);
            Bitmap scaledShape2 = Bitmap.createScaledBitmap(shape2, width, height, true);
            canvas.drawBitmap(scaledShape2, 0, 0, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            Bitmap scaledShape = Bitmap.createScaledBitmap(bitmap, width, height, true);
            canvas.drawBitmap(scaledShape, 0, 0, paint);
            return paintingBoard;
        } catch (Exception exp) {
            exp.printStackTrace();
            return bitmap;
        }
    }


}