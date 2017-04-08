package server;

import java.util.HashMap;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;

import bean.PushClientBean;

public class ClientPushSocket {
	
	public static HashMap<String, PushClientBean> clients = new HashMap<>();
	
	//增加一个客户端
	public static void addClient(SocketIOClient client){
		HandshakeData data = client.getHandshakeData();
		String account = data.getSingleUrlParam("account");
		String password = data.getSingleUrlParam("password");
		PushClientBean bean = new PushClientBean(account,password,client);
		clients.put(account, bean);
	}
	
	//移除一个客服端
	public static void removeClient(SocketIOClient client){
		HandshakeData data = client.getHandshakeData();
		String account = data.getSingleUrlParam("account");
		clients.remove(account);
	}
	
	public static void sendTestData(String data){
		if(clients.size() > 0){
			sendStringToClint(clients.get("123456").getClient(), "test", data);
		}
	}
	
	public static void sendStringToClint(SocketIOClient client,String msgType,String data){
		String[] arg1 = new String[]{data};
		client.sendEvent(msgType, (Object[])arg1);
	}

}
