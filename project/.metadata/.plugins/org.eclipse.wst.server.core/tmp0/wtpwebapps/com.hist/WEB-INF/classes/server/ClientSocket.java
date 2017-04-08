package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import bean.DeviceBean;

public class ClientSocket {
	
	public static List<DeviceBean> sockets;
	
	public static void addSocket(Socket socket){
		if (sockets == null) {
			sockets = new ArrayList<>();
		}
		System.out.println("server is receive a socket!!!");
		DeviceBean bean = new DeviceBean(socket);
		sockets.add(bean);
	}
	
	public static void removeSocket(Socket socket){
		for (int i = 0; i < sockets.size(); i++) {
			DeviceBean bean = sockets.get(i);
			if (bean.getSocket() == socket) {
				sockets.remove(bean);
				System.out.println("server remove a socket!!!");
				break;
			}
		}
	}
	
	public static void writeToSocket(int d){
		
	}

}