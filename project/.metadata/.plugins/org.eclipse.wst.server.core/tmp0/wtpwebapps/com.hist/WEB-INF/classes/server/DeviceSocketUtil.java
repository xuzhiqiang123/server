package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import bean.DeviceBean;

public class DeviceSocketUtil {
	
	public static List<DeviceBean> devices = new ArrayList<>();
	
	public static void addDevice(Socket socket){
		DeviceBean bean = new DeviceBean(socket);
		devices.add(bean);
	}
	
	public static void removeDevice(Socket socket){
		devices.remove(socket);
	}

}
