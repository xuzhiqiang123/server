package net;

import com.client.project.MyApplication;

/**
 * Created by YYBJ on 2017/3/11.
 */

public class NetWorkConstant {
//    public static final String URL_IM_SERVER = "http://139.199.221.66:30002";
    public static final String URL_IM_SERVER = "http://192.168.121.115:30002?id="+ MyApplication.getInstance().getUseView().id;
}
