package test;

import org.redisson.client.protocol.pubsub.Message;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import io.netty.channel.unix.Socket;

public class Demo {
	public static void main(String[] args) {
		Configuration config = new Configuration();
		config.setHostname("192.168.121.115");
		config.setPort(9092);
		SocketIOServer server = new SocketIOServer(config);
		System.out.println("--------------");
		server.addConnectListener(new ConnectListener() {
			
			@Override
			public void onConnect(SocketIOClient client) {
				System.out.println("连接成功");
				client.sendEvent("msg1", "消息1");
				client.sendEvent("msg2", "消息2");
				client.sendEvent("message", "message");
			}
		});
		server.addDisconnectListener(new DisconnectListener() {
			
			@Override
			public void onDisconnect(SocketIOClient arg0) {
				System.out.println("离线了");
			}
		});
		// 启动服务
		server.start();
		/*try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		server.stop();*/
	}
}
