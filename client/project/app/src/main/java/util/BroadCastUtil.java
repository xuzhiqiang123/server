package util;

import android.content.Intent;

/**
 * Created by YYBJ on 2017/3/13.
 */

public class BroadCastUtil {

    public static final String HOME_ACTIVITY_BROADCAST_ACTION = "homeActivity.broadcast.action";
    public static final int HOME_BROADCAST_DATA_TEST = 0;
    public static final int HOME_BROADCAST_IM_CONNECT = 1;
    public static final int HOME_BROADCAST_IM_DISCONNECT = 2;

    public static void sendHomeBroadcast(int type, Intent intent){
        intent.putExtra("type",type);
        intent.setAction(HOME_ACTIVITY_BROADCAST_ACTION);
        UIUtils.getContext().sendBroadcast(intent);
    }


}
