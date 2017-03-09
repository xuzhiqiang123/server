package server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;

import bean.PushBean;

public class ClientSocketIOListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent context) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		System.out.println("ClientSocketIOListener...0");
		Configuration configuration = new Configuration();
		configuration.setHostname("localhost");
		configuration.setPort(9092);
		SocketIOServer server = new SocketIOServer(configuration);
        // chateventΪ�¼�����
//        server.addEventListener("chatevent", ChatObject.class, new ChatObject());
		server.addEventListener("chatevent", PushBean.class, new ChatObject(server));
        //��������
        server.start();
        server.addConnectListener(new ConnectListener() {
			
			@Override
			public void onConnect(SocketIOClient client) {
				if (client != null) {
					System.out.println("����һ���ͷ���");
				}else {
					System.out.println("nonono...");
				}
			}
		});
	}
	
	public class ChatObject implements DataListener<PushBean>{
		
		private SocketIOServer server;
		
		public ChatObject(SocketIOServer server) {
			this.server = server;
		}

		@Override
		public void onData(SocketIOClient arg0, PushBean arg1, AckRequest arg2) throws Exception {
			server.getBroadcastOperations().sendEvent("chatevent", new PushBean("socketio","123456"));
			System.out.println("����һ������");
		}
		
	}

}
