package net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import util.MapUtils;


public class HttpUtil {

    private static ConnectivityManager mConnectivityManager;

    public static ConnectivityManager getConnectivityManager(Context context) {
        if (mConnectivityManager == null) {
            mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return mConnectivityManager;
    }


    public static boolean isConnected(Context context) {
        final NetworkInfo networkinfo = getConnectivityManager(context).getActiveNetworkInfo();
        if (networkinfo != null) {
            return networkinfo.isConnected();
        }

        return false;
    }

    /**
     * 获取当前设备的MAC地址
     */
    public static String getMacAddress(Context context) {
        String mac = null;
        try {
            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wm.getConnectionInfo();
            mac = info.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }

    /**
     * 获取网络类型id
     *
     * @param context
     * @return 0:无网络,2:wifi、3:cmwap、4:cmnet、5:ctnet、6:ctwap、7:3gwap、8:3gnet、9:uniwap、10:uninet
     */
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int NETWORK_TYPE_WIFI = 2;
    public static final int NETWORK_TYPE_CMWAP = 3;
    public static final int NETWORK_TYPE_CMNET = 4;
    public static final int NETWORK_TYPE_CTNET = 5;
    public static final int NETWORK_TYPE_CTWAP = 6;
    public static final int NETWORK_TYPE_3GWAP = 7;
    public static final int NETWORK_TYPE_3GNET = 8;
    public static final int NETWORK_TYPE_UNIWAP = 9;
    public static final int NETWORK_TYPE_UNINET = 10;

    public static int getNetworkTypeId(Context context) {
        try {
            final String type = getNetworkType(context);
            if ("wifi".equalsIgnoreCase(type)) {
                return NETWORK_TYPE_WIFI;
            } else if ("cmwap".equalsIgnoreCase(type)) {
                return NETWORK_TYPE_CMWAP;
            } else if ("cmnet".equalsIgnoreCase(type)) {
                return NETWORK_TYPE_CMNET;
            } else if ("ctnet".equalsIgnoreCase(type)) {
                return NETWORK_TYPE_CTNET;
            } else if ("ctwap".equalsIgnoreCase(type)) {
                return NETWORK_TYPE_CTWAP;
            } else if ("3gwap".equalsIgnoreCase(type)) {
                return NETWORK_TYPE_3GWAP;
            } else if ("3gnet".equalsIgnoreCase(type)) {
                return NETWORK_TYPE_3GNET;
            } else if ("uniwap".equalsIgnoreCase(type)) {
                return NETWORK_TYPE_UNIWAP;
            } else if ("uninet".equalsIgnoreCase(type)) {
                return NETWORK_TYPE_UNINET;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NETWORK_TYPE_UNKNOWN;
    }

    /**
     * 获取网络类型
     *
     * @param context
     * @return
     */
    public static String getNetworkType(Context context) {

        String netTypeMode = "";
        try {
            final NetworkInfo mNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
            if (mNetworkInfo == null) {
                return "";
            }
            final int netType = mNetworkInfo.getType();
            if (netType == ConnectivityManager.TYPE_WIFI) {
                // wifi上网
                netTypeMode = "wifi";
            } else if (netType == ConnectivityManager.TYPE_MOBILE) {
                // 接入点上网
                final String netMode = mNetworkInfo.getExtraInfo();
                if (!TextUtils.isEmpty(netMode)) {
                    return netMode;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return netTypeMode;
    }

    /**
     * 获取ip地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }


    /**
     * 拼接参数到url
     *
     * @param url
     * @param params
     */
    public static String concatUrl(String url, Map<String, Object> params, String charset) {
        if (MapUtils.hasData(params)) {
            StringBuilder builder = new StringBuilder(url);

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (builder.toString().contains("?")) {
                    builder.append("&");
                } else {
                    builder.append("?");
                }
                try {
                    builder.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue() + "", charset));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            return builder.toString();
        } else {
            return url;
        }
    }


    public static byte[] gzip(String data) {
        ByteArrayOutputStream arr = null;
        try {
            arr = new ByteArrayOutputStream();
            OutputStream zipper = new GZIPOutputStream(arr);
            zipper.write(data.getBytes("UTF-8"));
            zipper.close();
            return arr.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                arr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
