package util.image;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import util.LogUtil;
import util.UIUtils;

/**
 * Created by focux on 2016-3-23 .
 */
public class ImageLoader {
    private static final String TAG = "ImageLoader";


    public static void loadRound(ImageView view, String url, int roundInDP) {
        load(view, url, roundInDP, 0, false);
    }

    public static void load(ImageView view, String url) {
        load(url,view,0,0, FormDisplay.NONE,0,false,true,null);

    }
    public static void load(ImageView view, String url,int defaultResid,int errorResId,boolean cacheOnDisk,ImageLoadingListener imageLoadingListener) {
        load(url,view,defaultResid,errorResId, FormDisplay.NONE,0,false,cacheOnDisk,imageLoadingListener);

    }

    public static void load(ImageView view, String url, int defaultResId) {
        load(url,view,defaultResId,0, FormDisplay.NONE,0,false,true,null);
    }

    public static void load(ImageView view, String url, int defaultResId, int errorResId) {
        load(url,view,defaultResId,errorResId, FormDisplay.NONE,0,false,true,null);
    }


    public static void load(ImageView view, String url, int roundInDP, int defaultResId, boolean centerCrop) {
        load(view, url, roundInDP, defaultResId, 0, centerCrop);
    }

    /**
     * 一般加载图片
     *
     * @param view
     * @param url
     * @param roundInDP    图片显示圆角，默认0，不带圆角,单位dp
     * @param defaultResId
     * @param errorResId   出错图
     */
    public static void load(ImageView view, String url, int roundInDP, int defaultResId, int errorResId, boolean centerCrop) {
//        DrawableTypeRequest<String> request = getRequestManager().load(url);
//        if (defaultResId > 0) {
//            request.placeholder(defaultResId);
//        }
//        if (errorResId > 0) {
//            request.error(errorResId);
//        }
//        if (centerCrop) {
//            request.centerCrop();
//        }
//        if (roundInDP > 0) {
//            request.transform(new GlideRoundTransform(UIUtils.getContext(), roundInDP));
//        }
//        request.into(view);

        load(url,view,defaultResId,errorResId, FormDisplay.CORNER,roundInDP*2,centerCrop,true,null);
    }


    /**
     * 加载圆形图片
     *
     * @param view
     * @param url  图片地址
     */
    public static void loadCircle(ImageView view, String url) {
        loadCircle(view, url, 0, true);
    }

    /**
     * 加载圆形图片
     *
     * @param view
     * @param url          图片地址
     * @param defaultResId 占位图
     * @param centerCrop   图片居中裁剪，默认false
     */
    public static void loadCircle(ImageView view, String url, int defaultResId, boolean centerCrop) {
        LogUtil.i(TAG, "loadCircle URL >> " + url);
//        DrawableTypeRequest<String> request = getRequestManager().load(url);
//
//        if (defaultResId > 0) {
//            request.placeholder(defaultResId);
//        }
//        if (centerCrop) {
//            request.centerCrop();
//        }
//
//        request.crossFade();
//        request.transform(new GlideCircleTransform(view.getContext()));
//        request.into(view);

        load(url,view,defaultResId,0, FormDisplay.CIRCLE,0,centerCrop,true,null);
    }

    /**
     * 加载顶部圆角图片
     *
     * @param view
     * @param url          图片地址
     * @param roundInDip   圆角度数 单位dp
     * @param defaultResId 占位图
     * @param centerCrop   图片居中裁剪，默认false
     */
    public static void loadTopRound(ImageView view, String url, int roundInDip, int defaultResId, boolean centerCrop) {
        LogUtil.i(TAG, "loadTopRound URL >> " + url);
//        DrawableTypeRequest<String> request = getRequestManager().load(url);
//
//        if (defaultResId > 0) {
//            request.placeholder(defaultResId);
//        }
//        if (centerCrop) {
//            request.centerCrop();
//        }
//        request.crossFade();
//        request.bitmapTransform(new GlideTopRoundTransform(UIUtils.getContext(), roundInDip));
//        request.into(view);

        load(url,view,defaultResId,0, FormDisplay.TOPROUND,roundInDip*2,centerCrop,true,null);
    }

    /**
     * 加载倒角圆边图片
     *
     * @param view
     * @param url          图片地址
     * @param defaultResId 占位图
     * @param centerCrop   图片居中裁剪，默认false
     */
    public static void loadRoundCircle(ImageView view, String url, int defaultResId,int errorRestId, boolean centerCrop) {
        LogUtil.i(TAG, "loadCircle URL >> " + url);
//        DrawableTypeRequest<String> request = getRequestManager().load(url);
//
//        if (defaultResId > 0) {
//            request.placeholder(defaultResId);
//        }
//        if (errorRestId > 0) {
//            request.error(errorRestId);
//        }
//        if (centerCrop) {
//            request.centerCrop();
//        }
//
//
//        request.crossFade();
//        request.transform(new GlideRoundSideTransform(view.getContext()));
//        request.into(view);

        load(url,view,defaultResId,errorRestId, FormDisplay.ROUND_CIRCLE,0,centerCrop,true,null);
    }
    /**
     * 加载倒角圆边图片
     *
     * @param view
     * @param url          图片地址
     * @param defaultResId 占位图
     * @param centerCrop   图片居中裁剪，默认false
     */
    public static void loadRoundCircle(ImageView view, String url, int defaultResId, boolean centerCrop) {
       loadRoundCircle(view,url,defaultResId,0,centerCrop);
    }

    /**
     * 加载倒角圆边图片
     * 默认没有占位图 居中裁剪
     *
     * @param view
     * @param url  图片地址
     */
    public static void loadRoundCircle(ImageView view, String url) {
        loadRoundCircle(view, url, 0, true);
    }

    /**
     * 加载倒角圆边图片（在线）
     *
     * @param view
     * @param url          图片地址
     * @param defaultResId 占位图
     * @param centerCrop   图片居中裁剪，默认false
     */
    public static void loadRoundCircleOnline(ImageView view, String url, int defaultResId, boolean centerCrop) {
        LogUtil.i(TAG, "loadCircle URL >> " + url);
//        DrawableTypeRequest<String> request = getRequestManager().load(url);
//
//        if (defaultResId > 0) {
//            request.placeholder(defaultResId);
//        }
//        if (centerCrop) {
//            request.centerCrop();
//        }
//
//        request.crossFade();
//        request.transform(new GlideRoundSideOnlineTransform(view.getContext()));
//        request.into(view);

        load(url,view,defaultResId,0, FormDisplay.ROUNDCIRCLE_ONLINE,0,centerCrop,true,null);
    }



    /**
     * 加载倒角圆边图片(在线)
     * 默认没有占位图 居中裁剪
     *
     * @param view
     * @param url  图片地址
     */
    public static void loadRoundCircleOnline(ImageView view, String url) {
        loadRoundCircleOnline(view, url, 0, true);
    }

    /**
     * 加载圆形图片   本地图片
     */
    public static void loadCircle(ImageView view, int resId) {
//        DrawableTypeRequest<Integer> request = getRequestManager().load(resId);
//
//        request.transform(new GlideCircleTransform(view.getContext()));
//        request.into(view);

        load(getResourceUri(resId),view,0,0, FormDisplay.CIRCLE,0,false,true,null);
    }


    public enum FormDisplay{
        NONE,CIRCLE,CORNER,TOPROUND,ROUND_CIRCLE,ROUNDCIRCLE_ONLINE
    }

    private static String getResourceUri(int res){
        return "drawable://" + res;
    }

    static DisplayImageOptions.Builder build;
    /**
     *
     * Acceptable URIs examples

     "http://site.com/image.png" // from Web
     "file:///mnt/sdcard/image.png" // from SD card
     "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
     "content://media/external/images/media/13" // from content provider
     "content://media/external/video/media/13" // from content provider (video thumbnail)
     "assets://image.png" // from assets
     "drawable://" + R.drawable.img // from drawables (non-9patch images)
     * @param cacheOnDisk   在加载本地图片时不需要缓存到本地
     * */
    public static void load(String imageUri, ImageView imageView, int defaultResId, int errorResId, FormDisplay formDisplay, int cornerRadiusPixels, boolean centerCrop,boolean cacheOnDisk, ImageLoadingListener loadingListener){
        try {
            if (imageView == null) {
                return;
            }

            if (centerCrop) {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }

            if (imageUri==null) {
                if (errorResId>0) {
                    imageView.setImageResource(errorResId);
                }else if (defaultResId>0){
                    imageView.setImageResource(defaultResId);
                }
                return;
            }


            if (imageUri.startsWith("/")) {
                //local file
                imageUri = "file://"+imageUri;
            }

            initImageLoader(UIUtils.getContext());
            if (build == null) {
                build = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(cacheOnDisk)
    //                .bitmapConfig(Bitmap.Config.RGB_565)
                    //.considerExifParams(true)
                    ;
            }


            if (defaultResId>0) {
                build.showImageOnLoading(defaultResId);
                build.showImageForEmptyUri(defaultResId);
            }else {
                build.showImageOnLoading(0);
                build.showImageForEmptyUri(0);
            }
            if (errorResId>0) {
                build.showImageOnFail(errorResId);
            }else {
                build.showImageOnFail(0);
            }
            switch (formDisplay) {
                case CORNER:
                    build.displayer(new RoundedBitmapDisplayer(cornerRadiusPixels));
                    break;
                case CIRCLE:
                    build.displayer(new CircleBitmapDisplayer(Color.WHITE, 0));
                    break;
                case TOPROUND:
                    build.displayer(new TopRoundBitmapDisplayer());
                    break;
                case ROUND_CIRCLE:
                    build.displayer(new RoundSideBitmapDisplayer());
                    break;
                case ROUNDCIRCLE_ONLINE:
                    build.displayer(new RoundSideOnlineBitmapDisplayer());
                    break;
                default:
                    build.displayer(DefaultConfigurationFactory.createBitmapDisplayer());
                    break;
            }


//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_stub) // resource or drawable
//                .showImageForEmptyUri(R.drawable.ic_empty) // resource or drawable
//                .showImageOnFail(R.drawable.ic_error) // resource or drawable
//                .resetViewBeforeLoading(false)  // default
//                .delayBeforeLoading(1000)
//                .cacheInMemory(false) // default
//                .cacheOnDisk(false) // default
//                //.preProcessor(...)
//                //.postProcessor(...)
//                //.extraForDownloader(...)
//                //.considerExifParams(false) // default
//                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
//                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
//                //.decodingOptions(...)
//                //.displayer(new SimpleBitmapDisplayer()) // default
//                //.handler(new Handler()) // default
//                .build();

            com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(imageUri, new ImageViewAware(imageView), build.build(),loadingListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load( ImageView imageView,String imageUri, BitmapDisplayer displayer){
       load(imageView,imageUri,0,displayer);
    }
    public static void load( ImageView imageView,String imageUri,int defaultResId, BitmapDisplayer displayer){
        try {
            if (imageView == null) {
                return;
            }

            if (imageUri.startsWith("/")) {
                //local file
                imageUri = "file://"+imageUri;
            }

            initImageLoader(UIUtils.getContext());
            if (build == null) {
                build = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
    //                .bitmapConfig(Bitmap.Config.RGB_565)
                    //.considerExifParams(true)
                    ;
            }

            if (defaultResId>0) {
                build.showImageOnLoading(defaultResId);
                build.showImageForEmptyUri(defaultResId);
            }else {
                build.showImageOnLoading(0);
                build.showImageForEmptyUri(0);
            }

            build.displayer(displayer);

            com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(imageUri, new ImageViewAware(imageView), build.build(),null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** 防止init错误 */
    public static com.nostra13.universalimageloader.core.ImageLoader getInstance(){
        initImageLoader(UIUtils.getContext());
        return com.nostra13.universalimageloader.core.ImageLoader.getInstance();
    }

    //---------------UIL imageload temp

    private static void initImageLoader(Context context) {
        if (com.nostra13.universalimageloader.core.ImageLoader.getInstance().isInited()) {
            return;
        }
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY);
        //config.denyCacheImageMultipleSizesInMemory();
        config.memoryCacheSize((int) Runtime.getRuntime().maxMemory() / 16);
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(100 * 1024 * 1024); // 10 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        //修改连接超时时间5秒，下载超时时间5秒
        config.imageDownloader(new BaseImageDownloader(context, 30* 1000, 30 * 1000));
        //		config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config.build());

    }

    //end------UIL--------------


}
