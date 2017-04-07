package util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

/**
 * Created by DongSheng on 2015/7/2.
 */
public class SharePreferenceUtil{

    private static final String FILENAME = "gg";
    public static final String FIRST_LOGIN = "first_login";//是否登录
    public static final String LOGIN_USE_VIEW = "login_use_view";//用户的信息

    public static boolean getBoolean(String key, boolean defValue){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  preferences.getBoolean(key,defValue);
    }

    public static void saveBoolean(String key, boolean value){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key,value);
        edit.apply();
    }
    public static String getString(String key, String defValue){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);

        return  preferences.getString(key, defValue);
    }

    public static void saveString(String key, String defValue){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key,defValue);
        edit.apply();
    }

    public static void saveBoolean(String key, String value){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.apply();
    }


    public static void saveInt(String key, int  value){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public static int getInt(String key){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  preferences.getInt(key, 0);
    }

    public static int getInt(String key,int def){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  preferences.getInt(key, def);
    }

    public static void saveLong(String key, long value){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putLong(key, value);
        edit.apply();
    }

    public static long getLong(String key){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  preferences.getLong(key, 0);
    }

    private static final String IDNO_STATUS = "idno_status";
    /**
     * 保存身份证的验证状态
     * @param status 身份证状态
     */
    public static void saveIdnoStatus(int status){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(IDNO_STATUS, status);
        edit.apply();
    }

    /**
     *获取本地的身份证验证状态
     * @return  0：默认，1：审核通过，2 审核中：
     */
    public static int getIdnoStatus(){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  preferences.getInt(IDNO_STATUS, 0);
    }


    public static final String PAY_STATUS = "pay_status";

    /**
     * 保存该用户是否付费
     * @param payStatus 用户的付费状态
     */
    public static void savePayStatus(int payStatus){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(PAY_STATUS, payStatus);
        edit.apply();
    }

    /**
     * 获取用户的付费的
     * @return 0 未付费，1 付费
     */
    public static int getPayStatus(){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  preferences.getInt(PAY_STATUS, 0);
    }



    public static final String WECHAT_CERTI_STATUS = "wechat_certi_status";
    /**
     * 保存微信的验证状态
     * @param status 验证状态 0未认证 1已认证
     */
    public static void saveWeChatCertiStatus(int status){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(WECHAT_CERTI_STATUS, status);
        LogUtil.i("saveWeChatCertiStatus",status + "");
        edit.apply();
    }

    /**
     *获取本地的微信证验证状态
     * @return  0未认证 1已认证
     */
    public static int getWeChatCertiStatus(){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  preferences.getInt(WECHAT_CERTI_STATUS, 0);
    }


    //////////////////////////////////////////////////以下是女性用户授权模块添加//////////////////////////////////////
    //添加保存和获取回赞的个数

    /**记录回赞次数后缀*/
    public static String KEY_REPLAY_PRAISE_COUNT_SUF="_reply_praise_count";


    public static final String USER_REGISTRE_STEP="USER_REGISTRE_STEP";
    /**设置当前登录用户，给某个用户发送过消息前缀*/
    private static String suffix="MsgWho";
    /**
     * 设置当前登录用户，给某个用户发送过消息
     * @param msgToWhoKey 规则，当前登录用户id+"_"+接受消息的用户id
     */
    public static void setHasSendMsgToWho(String msgToWhoKey){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(suffix+msgToWhoKey,"1");
        edit.commit();
    }

    /**
     * 获取当前用户是否给某个用户发送过消息
     * @param msgToWhoKey
     * @return true：标示发送过，false:表示没有发送过
     */
    public static boolean getHasSendMsgToWho(String msgToWhoKey){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        String value=preferences.getString(suffix+msgToWhoKey, "0");
        if("1".equals(value)){
            return true;
        }
        return  false;
    }





    /**用户引导时间状态*/
    public static String USER_GUIDE_TIME_STATE="USER_GUIDE_TIME";
    /**用户引导操作状态*/
    public static String USER_GUIDE_OP_STATE="USER_GUIDE_OP";


    /**
     * 获取用户引导时间状态
     * @param state
     */
    public static void saveUserGuideTimeState(int state){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(USER_GUIDE_TIME_STATE,state);
        edit.commit();
    }

    /**
     * 获取用户引导时间状态
     * @return
     */
    public static int getUserGuideTimeState(){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        int value=preferences.getInt(USER_GUIDE_TIME_STATE, 0);
        return  value;
    }
    /**
     * 获取用户引导时间状态
     * @param state
     */
    public static void saveUserGuideOpState(int state){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(USER_GUIDE_OP_STATE,state);
        edit.commit();
    }

    /**
     * 获取用户引导时间状态
     * @return
     */
    public static int getUserGuideOpState(){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        int value=preferences.getInt(USER_GUIDE_OP_STATE, 0);
        return  value;
    }

    private static String LIKE_DYNAMIC_STATE="like_dynamic_state_";

    /**
     * 保存动态喜欢的状态
     * @param contentId
     */
    public static void saveUserDynamicLikedState(String contentId){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(LIKE_DYNAMIC_STATE+contentId,true);
        edit.commit();
    }

    /**
     * 获取动态喜欢的状态
     * @param contentId
     * @return
     */
    public static boolean getUserDynamicLikedState(String contentId){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        boolean value=preferences.getBoolean(LIKE_DYNAMIC_STATE+contentId, false);
        return  value;
    }

    /**
     * 根据key获取动态引导的状态值
     * @param key key值是day值_6/12/18/24
     * @return
     */
    public static boolean getDynamicTipsByKey(String key){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        boolean value=preferences.getBoolean(key, false);
        return value;
    }
    public static void saveDynamicTipsByKey(String key){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key,true);
        edit.commit();
    }
    /**
     * 根据key获取发布动态是否添加过心情标签状态值
     * @param key key值是day值_6/12/18/24
     * @return
     */
    public static boolean getIfAddEmotionByDay(String key){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        boolean value=preferences.getBoolean(key+"_emotion", false);
        return value;
    }
    public static void saveIfAddEmotionByDay(String key){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key+"_emotion",true);
        edit.commit();
    }


    /**获取上一次首次登录的时间*/
    public static Long getLastFirstLoginTime(String uid) {
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        Long time =preferences.getLong(uid+"last_first_login_time",0);
        return time;
    }

    /**
     * 添加已经谢谢的用户列表
     * @param hasTksList
     */
    public static void addHasTksList(List<String> hasTksList) {
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        StringBuffer strBuffer=new StringBuffer();
        strBuffer.append(getHasTksList());
        for (String hasTksId : hasTksList) {
            strBuffer.append(hasTksId+",");
        }
        edit.putString("hasTksList",strBuffer.toString());
        edit.commit();
    }

    /**
     * 获取已经谢谢过的数据
     * @return
     */
    public static String getHasTksList(){
        SharedPreferences preferences = UIUtils.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return preferences.getString("hasTksList","");
    }

}
