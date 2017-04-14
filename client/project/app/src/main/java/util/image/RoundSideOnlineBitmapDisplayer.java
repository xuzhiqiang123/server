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

public class RoundSideOnlineBitmapDisplayer implements BitmapDisplayer {

    public RoundSideOnlineBitmapDisplayer() {
    }

    @Override
    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        imageAware.setImageBitmap(createCircleImage(bitmap,bitmap.getWidth(),bitmap.getHeight()));

    }

    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param source
     * @param width
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int width, int height) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_4444);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
//        canvas.drawCircle(min / 2, min / 2, min / 2, paint);


//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        Bitmap shape2 = BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.head_bg_layer);
        Bitmap scaledShape2 = Bitmap.createScaledBitmap(shape2, width, width, true);
        canvas.drawBitmap(scaledShape2, 0, 0, paint);
        /**
         * 使用SRC_IN
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        /**
         * 绘制图片
         */
        Bitmap scaledSource = Bitmap.createScaledBitmap(source, width, width, true);
        canvas.drawBitmap(scaledSource, 0, 0, paint);

        /**
         * 绘制在线标志
         * */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        Bitmap onLine = BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.login_label_online);
        int onLineWidth = width / 3;
        int onLineLeft = width / 3 * 2 - 2;
        Bitmap scaledOnLine = Bitmap.createScaledBitmap(onLine, onLineWidth, onLineWidth, true);
        canvas.drawBitmap(scaledOnLine, onLineLeft, 2, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

        Bitmap shape = BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.head_bg_over);
        Bitmap scaledShape = Bitmap.createScaledBitmap(shape, width, width, true);
        canvas.drawBitmap(scaledShape, 0, 0, paint);
        return target;
    }




}