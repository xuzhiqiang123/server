package util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 与轮询相关的工具类
 * @author solo
 */
public class PollingUtil {
	
	private static final String TAG = PollingUtil.class.getSimpleName();
	
	/**
	 * 判断当前进程是否是后台进程
	 * @param context
	 * @return true 是  ；false 否
	 */
	public static boolean isAppBackGroud(Context context){
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo>  appProcesses  = manager.getRunningAppProcesses();
		//判断当前是否有正在运行的程序，如果为空，则没有，如果不为空，则有
		if(appProcesses != null){
			for(RunningAppProcessInfo appProcessInfo : appProcesses){
				//判断一个进程是不是我们自己的进程
				if(appProcessInfo.processName.equals(context.getPackageName())){
					if(appProcessInfo.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND){
						Log.i(TAG, "it's a backgound process ::" + appProcessInfo.processName);
						return true;
					}else{
						Log.i(TAG, "it's foregound process ::" + appProcessInfo.processName);
						return false;
					}
				}
			}
		}
		return false;
	}


	/**
	 *判断当前应用程序处于前台还是后台
	 */
	public static boolean isApplicationBroughtToBackground(final Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				Log.i(TAG, "it's a backgound process ::");
				return true;
			}
		}
		Log.i(TAG,"it's a foregound process::");
		return false;
	}


	public static String getTopActivity(Activity context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
		if (runningTaskInfos != null)
			return (runningTaskInfos.get(0).topActivity.getClassName()).toString();
		else
			return null;
	}



	
	/**
	 * 启动信箱轮询服务
	 * @param context
	 * @param time 轮询间隔的时间
	 * @param cls 要执行轮询的服务
	 * @param action 启动服务时，所需要的action
	 */
	public static void startPollingService(Context context , long time, Class<?> cls, String action){
		AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context,cls);
		intent.setAction(action);
		PendingIntent pendingIntent =
				PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), time, pendingIntent);

	}


	/*private void registerExactAlarm(Context mContext ,PendingIntent sender, long delayInMillis) {
		final int SDK_INT = Build.VERSION.SDK_INT;
		AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
		long timeInMillis = (System.currentTimeMillis() + delayInMillis) / 1000 * 1000;     //> example

		if (SDK_INT < Build.VERSION_CODES.KITKAT) {
			am.set(AlarmManager.RTC_WAKEUP, timeInMillis, sender);
		}
		else if (Build.VERSION_CODES.KITKAT <= SDK_INT  && SDK_INT < Build.VERSION_CODES.M) {
			am.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, sender);
		}
		else if (SDK_INT >= Build.VERSION_CODES.M) {
			am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMillis, sender);
		}
	}
	*/
	
	/**
	 * 停止信箱轮询服务
	 * @param context
	 * @param cls
	 * @param action
	 */
	public static void stopPollingService(Context context, Class<?> cls, String action){
		AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = 
				PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		manager.cancel(pendingIntent);
		Log.i(TAG, "stop polling service");
	}
	
	/**
	 * 启动消息轮询服务
	 * @param context
	 * @param time 轮询间隔的时间
	 * @param cls 要执行轮询的服务
	 * @param action 启动服务时，所需要的action
	 */
	public static void startMsgPollingService(Context context , int time, Class<?> cls, String action){
		AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context,cls);
		intent.setAction(action);
		PendingIntent pendingIntent = 
				PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		long triggerAtTime = SystemClock.elapsedRealtime();
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime, time, pendingIntent);
		Log.i(TAG, "start polling service");
	}
	
	
	/**
	 * 停止消息轮询服务
	 * @param context
	 * @param cls
	 * @param action
	 */
	public static void stopMsgPollingService(Context context, Class<?> cls, String action){
		AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = 
				PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		manager.cancel(pendingIntent);
		Log.i(TAG, "stop polling service");
	}


	/**
	 * 判断是否锁屏
	 * @param c
	 * @return
	 */
	public final static boolean isScreenLocked(Context c) {
		KeyguardManager mKeyguardManager = (KeyguardManager) c.getSystemService(c.KEYGUARD_SERVICE);
		boolean isLocked = mKeyguardManager.inKeyguardRestrictedInputMode();
		Log.i(TAG,"is screen locked == " + isLocked);
		return isLocked;
	}


	/**
	 * 服务是否在运行
	 * @param className
	 * @return true 运行，false 停止
	 */
	public static boolean isServiceWorked(Context context,String className) {
		ActivityManager myManager = (ActivityManager)context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(30);
		for (int i = 0; i < runningService.size(); i++) {
			Log.i(TAG,"the service name is :: " + runningService.get(i).service.getClassName().toString());
			if (runningService.get(i).service.getClassName().toString().equals(className)) {
				return true;
			}
		}
		return false;
	}

}
