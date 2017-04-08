package bean;

import com.corundumstudio.socketio.SocketIOClient;

public class PushClientBean {
	
	private String id;
	private SocketIOClient client;
	
	public PushClientBean() {
	}
	
	public PushClientBean(String id, SocketIOClient client) {
		this.id = id;
		this.client = client;
	}

	public String getAccount() {
		return id;
	}
	public void setAccount(String id) {
		this.id = id;
	}
	
	public SocketIOClient getClient() {
		return client;
	}
	public void setClient(SocketIOClient client) {
		this.client = client;
	}
	
	
}
