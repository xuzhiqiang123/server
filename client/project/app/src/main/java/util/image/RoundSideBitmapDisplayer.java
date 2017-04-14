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

import util.ThreadManager;
import util.UIUtils;

//ROUNDCIRCLE
public class RoundSideBitmapDisplayer implements BitmapDisplayer {

    public RoundSideBitmapDisplayer() {
    }

    @Override
    public void display(final Bitmap bitmap, final ImageAware imageAware, LoadedFrom loadedFrom) {
        ThreadManager.getLongPool().execute(new Runnable() {
            @Override
            public void run() {
                final Bitmap circleImage = createCircleImage(bitmap, bitmap.getWidth(), bitmap.getHeight());
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        imageAware.setImageBitmap(circleImage);

                    }
                });
            }
        });

    }
    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param source
     * @param width
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int width, int height)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap    target = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
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
        canvas.drawBitmap(scaledShape2,0,0,paint);
        /**
         * 使用SRC_IN
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        /**
         * 绘制图片
         */
        Bitmap scaledSource = Bitmap.createScaledBitmap(source, width, width, true);
        canvas.drawBitmap(scaledSource, 0, 0, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

        Bitmap shape = BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.head_bg_over);
        Bitmap scaledShape = Bitmap.createScaledBitmap(shape, width, width, true);
        canvas.drawBitmap(scaledShape,0,0,paint);
        return target;
    }


}