package util;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.engineio.client.transports.WebSocket;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import net.NetWorkConstant;

import java.net.URISyntaxException;

import service.IMService;

/**
 * Created by YYBJ on 2017/3/11.
 */

public class IMConnect{

    private static com.github.nkzawa.socketio.client.Socket mSocket;
    private static final String TAG = "IMConnect";

    public static void connectIMServer() throws URISyntaxException {
        if (mSocket != null){
            mSocket.off();
            mSocket.close();
            while(mSocket.connected()){
                Log.i(TAG, "wait for socket close !!");
            }
            mSocket = null;
        }
        //socket配置
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        opts.reconnection = false;
        opts.transports = new String[]{WebSocket.NAME};
        mSocket = IO.socket(NetWorkConstant.URL_IM_SERVER,opts);
        setSocketListener();
    }

    public static boolean serverConnected(){
        if (mSocket != null && mSocket.connected()){
            return true;
        }
        return false;
    }

    private static void setSocketListener() {
        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                UIUtils.showToast("连接到服务器");
            }
        });

        mSocket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                UIUtils.showToast("断开连接");
            }
        });

        mSocket.on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                for (int i = 0; i < args.length; i++) {
                    Log.i(TAG,args[0].toString());
                }
           }
        });
    }

    /**
     * 打开IM服务
     */
    public static void startIMService() {
        UIUtils.getContext().startService(new Intent(UIUtils.getContext(), IMService.class));
        startRepeatService();
    }

    private static void startRepeatService() {
        if (PollingUtil.isServiceWorked(UIUtils.getContext(), "service.IMService")) {
            Log.i(TAG, "socket io polling service was already started ... ");
        } else {
            Log.i(TAG, "start socket io polling service ... ");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    PollingUtil.startPollingService(UIUtils.getContext(), 1 * 1000, IMService.class, "IMServer");
                }
            }, 1 * 1000);
        }
    }
}
