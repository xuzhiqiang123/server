package util;

import android.content.Intent;
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

public class IMConnect {

    private static Socket mSocket;
    private static final String TAG = "IMConnect";

    public static void connectIMServer() throws URISyntaxException {
        Log.e(TAG, "connectIMServer");
        if (mSocket != null && mSocket.connected()) {
            return;
        }
        if (mSocket != null) {
            mSocket.off();
            mSocket.close();
        }
        mSocket = null;
        //socket配置
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        opts.reconnection = false;
        opts.transports = new String[]{WebSocket.NAME};
        mSocket = IO.socket(NetWorkConstant.URL_IM_SERVER);
        setSocketListener();
    }

    private static void setSocketListener() {
        Log.e(TAG,"setSocketListener");
        //收到消息
        mSocket.on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                for (int i = 0; i < args.length; i++) {
                    Log.i(TAG, "receive message is: "+args[0].toString());
                    handleMessage(args[0].toString());
                }
            }
        });

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                UIUtils.showToast("连接到服务器");
                Log.i(TAG, "连接成功");
                BroadCastUtil.sendHomeBroadcast(BroadCastUtil.HOME_BROADCAST_IM_CONNECT, new Intent());
            }
        });

        mSocket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i(TAG, "断开连接");
                BroadCastUtil.sendHomeBroadcast(BroadCastUtil.HOME_BROADCAST_IM_DISCONNECT, new Intent());
            }
        });
        mSocket.connect();
    }

    //处理接收到信息
    private static void handleMessage(String msg) {

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
            UIUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    PollingUtil.startPollingService(UIUtils.getContext(), 60 * 1000, IMService.class, "IMServer");
                }
            }, 60 * 1000);
        }
    }
}
