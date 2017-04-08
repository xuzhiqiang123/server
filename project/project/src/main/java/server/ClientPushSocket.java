package server;

import java.util.HashMap;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;

import bean.PushClientBean;

public class ClientPushSocket {
	
	public static HashMap<String, PushClientBean> clients = new HashMap<>();
	
	//����һ���ͻ���
	public static void addClient(SocketIOClient client){
		HandshakeData data = client.getHandshakeData();
		String id = data.getSingleUrlParam("id");
		System.out.println("socketio--id:"+id);
		PushClientBean bean = new PushClientBean(id,client);
		clients.put(id, bean);
	}
	
	//�Ƴ�һ���ͷ���
	public static void removeClient(SocketIOClient client){
		HandshakeData data = client.getHandshakeData();
		String id = data.getSingleUrlParam("id");
		clients.remove(id);
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