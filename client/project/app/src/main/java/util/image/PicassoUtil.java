package util.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.renderscript.RenderScript;
import android.text.TextUtils;
import android.widget.ImageView;

import com.client.project.MyApplication;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

import util.LogUtil;
import util.StringUtils;


/**
 * Created by solo on 15/8/27.
 * QQ:1049447621
 * Version 1.0
 */
public class PicassoUtil {


    /**
     * 加载图片
     * @param url
     * @param imageView
     */
    public static void loadRoundImg(String url,ImageView imageView,int width,int height){
        LogUtil.i("Picasso",url);
        Picasso.with(MyApplication.getInstance())
                .load(url)
                .resize(width, height)
                .centerCrop()
                .into(imageView);
    }


    /**
     * 加载图片
     * @param url
     * @param imageView
     */
    public static void loadRoundImg(String url,ImageView imageView,int width,int height,int placeholder,int error){
        LogUtil.i("Picasso",url);
        if(TextUtils.isEmpty(url)){
            return;
        }
        Picasso.with(MyApplication.getInstance())
                .load(url)
                .resize(width, height)
                .placeholder(placeholder)
                .error(error)
                .centerCrop()
                .into(imageView);
    }

    public static void loadAvatarImg(String url,ImageView imageView,int width,int height,int placeholder,int error){
        if(TextUtils.isEmpty(url)){
            return;
        }
        LogUtil.i("Picasso", url);
        Picasso.with(MyApplication.getInstance())
                .load(url)
                .resize(width, height)
                .transform(new RoundTransformation())
                .placeholder(placeholder)
                .error(error)
                .centerInside()
                .into(imageView);
    }


    /**
     * 加在圆角图片
     */
    public static void loadRoundCornerImg(String url,ImageView imageView,int width,int height,int placeholder,int error){
        LogUtil.i("Picasso", url);
        if(TextUtils.isEmpty(url)){
            return;
        }
        Picasso.with(MyApplication.getInstance())
                .load(url)
                .resize(width, height)
                .transform(new RoundCornerTransformation())
                .placeholder(placeholder)
                .error(error)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 加在圆角图片
     */
    public static void loadRoundCornerImg(String url,ImageView imageView,int width,int height,int corner,int placeholder,int error){
        LogUtil.i("Picasso", url);
        if(TextUtils.isEmpty(url)){
            return;
        }
        Picasso.with(MyApplication.getInstance())
                .load(url)
                .resize(width, height)
                .transform(new RoundCornerTransformation(corner))
                .placeholder(placeholder)
                .error(error)
                .centerCrop()
                .into(imageView);
    }


    public static void loadRoundCornerImgGauss(String url,ImageView imageView,int width,int height,int corner,int placeholder,int error,Context context){
        LogUtil.i("Picasso", url);
        if(TextUtils.isEmpty(url)){
            return;
        }
        Picasso.with(MyApplication.getInstance())
                .load(url)
                .resize(width, height)
                .transform(new BlurTransformation(context))
                .transform(new RoundCornerTransformation(corner))
                .placeholder(placeholder)
                .error(error)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 加在圆角图片
     */
    public static void loadRoundCornerImg(File file,ImageView imageView,int width,int height,int placeholder,int error){
        if(file == null){
            return;
        }

        LogUtil.i("Picasso", file.getAbsolutePath());
        Picasso.with(MyApplication.getInstance())
                .load(file)
                .resize(width, height)
                .transform(new RoundCornerTransformation())
                .placeholder(placeholder)
                .error(error)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 加在圆角图片
     */
    public static void loadRoundCornerImgGuass(File file,ImageView imageView,int width,int height,int corner,int placeholder,int error, Context context){
        if(file == null){
            return;
        }

        LogUtil.i("Picasso", file.getAbsolutePath());
        Picasso.with(MyApplication.getInstance())
                .load(file)
                .resize(width, height)
                .transform(new BlurTransformation(context))
                .transform(new RoundCornerTransformation(corner))
                .placeholder(placeholder)
                .error(error)
                .centerCrop()
                .into(imageView);
    }



    /**
     * 加在圆角图片
     */
    public static void loadRoundCornerImg(File file,ImageView imageView,int width,int height,int corner,int placeholder,int error){
        if(file == null){
            return;
        }

        LogUtil.i("Picasso", file.getAbsolutePath());
        Picasso.with(MyApplication.getInstance())
                .load(file)
                .resize(width, height)
                .transform(new RoundCornerTransformation(corner))
                .placeholder(placeholder)
                .error(error)
                .centerCrop()
                .into(imageView);
    }



    /**
     * 加在圆角图片
     */
    public static void loadRoundBigCornerImg(String url,ImageView imageView,int width,int height,int placeholder,int error){
        LogUtil.i("Picasso", url);
        if(TextUtils.isEmpty(url)){
            return;
        }
        Picasso.with(MyApplication.getInstance())
                .load(url)
                .resize(width, height)
                .transform(new RoundCornerTransformation(10))
                .placeholder(placeholder)
                .error(error)
                .centerInside()
                .into(imageView);
    }


    /**
     * 从文件加载图片
     * @param file
     * @param imageView
     * @param width
     * @param height
     * @param placeholder
     * @param error
     */
    public static void loadAvatarImg(File  file,ImageView imageView,int width,int height,int placeholder,int error){
        LogUtil.i("Picasso",file.getAbsolutePath());
        Picasso.with(MyApplication.getInstance())
                .load(file)
                .resize(width, height)
                .transform(new RoundTransformation())
                .placeholder(placeholder)
                .error(error)
                .centerInside()
                .into(imageView);
    }





    /**
     * 加载图片
     * @param url
     * @param imageView
     */
    public static void loadRoundImg(String url,ImageView imageView,int width,int height,int error){

        LogUtil.i("Picasso",url);

        Picasso.with(MyApplication.getInstance())
                .load(url)
                .resize(width, height)
                .error(error)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 加载图片
     * @param url
     * @param imageView
     */
    public static void loadRoundImg(String url,ImageView imageView){

        LogUtil.i("Picasso", url);
        if(url.contains("res:///")){
               String[] urls =  url.split("///");
               loadResource(Integer.parseInt(urls[1]),imageView);
        }else{
            Picasso.with(MyApplication.getInstance())
                    .load(url)
                    .fit()
                    .into(imageView);
        }


    }

    /**
     * 加载图片
     * @param url
     * @param imageView
     */
    public static void loadSourceImg(String url,ImageView imageView){

        LogUtil.i("Picasso",url);
        Picasso.with(MyApplication.getInstance())
                .load(url)
                //.fit()
                .into(imageView);
    }


    public static void loadSourceImg(String url,ImageView imageView,int width,int height,int placeholder,int error ){
        LogUtil.i("Picasso",url);
        Picasso.with(MyApplication.getInstance())
                .load(url)
                .resize(width, height)
                .centerInside()
                .into(imageView);
    }



    /**
     * 从文件重加载图片
     * @param file
     * @param imageView
     * @param width
     * @param height
     */
    public static void loadImgFromFile(File file,ImageView imageView,int width,int height,int placeholder,int error) {
        Picasso.with(MyApplication.getInstance()
                .getApplicationContext())
                .load(file)
                .resize(width, height)
                .placeholder(placeholder)
                .error(error)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 从文件重加载图片
     * @param file
     * @param imageView
     * @param width
     * @param height
     */
    public static void loadImgFromFile(File file,ImageView imageView,int width,int height) {
        Picasso.with(MyApplication.getInstance()
                .getApplicationContext())
                .load(file)
                .resize(width, height)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 从文件重加载图片
     * @param imageView
     */
    public static void load(String uri,ImageView imageView) {
        if (uri == null) {
            return;
        }
        
        if (uri.startsWith("/")) {
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(new File(uri))
                    //.centerCrop()
                    .into(imageView);
        }else {
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(uri)
                    //.centerCrop()
                    .into(imageView);
        }
       
    }

    /**
     * 从文件重加载图片
     * @param imageView
     */
    public static void loadCenterCrop(String uri,ImageView imageView,int defaultResId,int width) {
        if (uri == null) {
            return;
        }

        if (uri.startsWith("/")) {
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(new File(uri))
                    .placeholder(defaultResId)
                    .resize(width,width)
                    .centerCrop()
                    .into(imageView);
        }else {
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(uri)
                    .placeholder(defaultResId)
                    .resize(width,width)
                    .centerCrop()
                    .into(imageView);
        }

    }
    /**
     * 从文件重加载图片
     * @param imageView
     */
    public static void loadCenterCrop(String uri,ImageView imageView,int width) {
        if (uri == null) {
            return;
        }

        if (uri.startsWith("/")) {
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(new File(uri))
                    .resize(width,width)
                    .centerCrop()
                    .into(imageView);
        }else {
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(uri)
                    .resize(width,width)
                    .centerCrop()
                    .into(imageView);
        }

    }

    /**
     * 从文件重加载图片
     * @param imageView
     */
    public static void loadCenterCropGauss(String uri,ImageView imageView,int width,Context context) {
        if (uri == null) {
            return;
        }

        if (uri.startsWith("/")) {
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(new File(uri))
                    .resize(width,width)
                    .centerCrop()
                    .transform(new BlurTransformation(context))
                    .into(imageView);
        }else {
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(uri)
                    .resize(width,width)
                    .centerCrop()
                    .transform(new BlurTransformation(context))
                    .into(imageView);
        }

    }

    public static void loadCenterCropGauss(String uri,ImageView imageView,Context context, int res) {
        if (uri == null) {
            return;
        }

        if (uri.startsWith("/")) {
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(new File(uri))
                    .placeholder(res)
                    .transform(new BlurTransformation(context))
                    .into(imageView);
        }else {
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(uri)
                    .placeholder(res)
                    .transform(new BlurTransformation(context))
                    .into(imageView);
        }

    }



    public static class BlurTransformation implements Transformation {

        RenderScript rs;

        public BlurTransformation(Context context) {
            super();
            rs = RenderScript.create(context);
        }

        @SuppressLint("NewApi")
        @Override
        public Bitmap transform(Bitmap bitmap) {
            Bitmap blurredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

//            // 分配内存
//            Allocation input = Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SHARED);
//            Allocation output = Allocation.createTyped(rs, input.getType());
//
//            // 根据我们想使用的配置加载一个实例
//            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
//            script.setInput(input);
//
//            // 设置模糊半径
//            script.setRadius(5);
//
//            //开始操作
//            script.forEach(output);
//
//            // 将结果copy到blurredBitmap中
//            output.copyTo(blurredBitmap);

            Bitmap bitmap1 = FastBlur.doBlur(blurredBitmap,55,true);
//            //释放资源
            bitmap.recycle();

            return bitmap1;
        }

        @Override
        public String key() {
            return "blur";
        }
    }



    /**
     * 从uri重加载图片
     * @param outPutUri
     * @param imageView
     * @param width
     * @param height
     */
    public static void loadImgFromUri(Uri outPutUri,ImageView imageView,int width,int height) {
        Picasso.with(MyApplication.getInstance()
                .getApplicationContext())
                .load(outPutUri)
                .resize(width, height)
                //.placeholder(R.drawable.default_pic)
                //.error(R.drawable.default_pic)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 从uri重加载图片
     * @param path  本地图片路径 or  URL
     * @param imageView
     */
    public static void loadTopRound(String path,ImageView imageView) {
        File file = null;
        if (!TextUtils.isEmpty(path) && !StringUtils.isUrl(path)) {
            file = new File(path);
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(file)
                    //.resize(width, height)
                    //.placeholder(R.drawable.default_pic)
                    //.error(R.drawable.default_pic)
                    .transform(new TopRoundTransform())
                    .into(imageView);
        }else {
            Picasso.with(MyApplication.getInstance()
                    .getApplicationContext())
                    .load(path)
//                    .resize(width, height)
//                    .placeholder(R.drawable.default_pic)
//                    .error(R.drawable.default_pic)
                    .transform(new TopRoundTransform())
                    .into(imageView);
        }

    }

    /**
     * 加载本地资源图片
     * @param resId
     * @param imageView
     * @param width
     * @param height
     */
    public static void loadResource(int resId,ImageView imageView,int width,int height){
        Picasso.with(MyApplication.getInstance()
                .getApplicationContext())
                .load(resId)
                .resize(width, height)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 加载本地资源图片
     * @param resId
     * @param imageView
     */
    public static void loadResource(int resId,ImageView imageView){
        Picasso.with(MyApplication.getInstance()
                .getApplicationContext())
                .load(resId)
               // .resize(width, height)
                //.centerCrop()
                .into(imageView);
    }



    /**
     * 加在圆角图片
     */
    public static void loadRoundBigCornerImg2(String url,ImageView imageView,int width,int height,int placeholder,int error){
        LogUtil.i("Picasso", url);
        if(TextUtils.isEmpty(url)){
            return;
        }
        Picasso.with(MyApplication.getInstance())
                .load(url)
                .resize(width, height)
                .transform(new RoundCornerTransformation(10))
                .placeholder(placeholder)
                .error(error)
                .centerCrop()
                .into(imageView);
    }
 
}
