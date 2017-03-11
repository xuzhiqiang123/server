package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.client.project.HomeActivity;

import java.net.URISyntaxException;

import util.IMConnect;
import util.ThreadManager;

/**
 * Created by YYBJ on 2017/3/11.
 */

public class IMService extends Service {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("IM", "onCreate: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Log.i("IM", "onStartCommand: ");
        Intent intent1 = new Intent(HomeActivity.HOME_ACTIVITY_BROADCAST_ACTION);
        sendBroadcast(intent1);
//        connectServer();
        return START_STICKY;
    }

    private void connectServer() {
        ThreadManager.getLongPool().execute(new Runnable() {
            @Override
            public void run() {
                if (!IMConnect.serverConnected()){
                    try {
                        IMConnect.connectIMServer();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
