package bean;

import com.corundumstudio.socketio.SocketIOClient;

public class PushClientBean {
	
	private String account;
	private String passward;
	private SocketIOClient client;
	
	public PushClientBean() {
	}
	
	public PushClientBean(String account, String passward, SocketIOClient client) {
		this.account = account;
		this.passward = passward;
		this.client = client;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassward() {
		return passward;
	}
	public void setPassward(String passward) {
		this.passward = passward;
	}
	public SocketIOClient getClient() {
		return client;
	}
	public void setClient(SocketIOClient client) {
		this.client = client;
	}
	
	
}
