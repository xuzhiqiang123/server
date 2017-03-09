package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import util.ThreadManager;

public class ServerSocketListener implements ServletContextListener{
	
	private ServerSocket server;
	private int port = 8888;
	
	private boolean connect = true;//�Ƿ�����Ӳ��

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if (server != null)
        {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		if (server == null) {
			try {
				server = new ServerSocket(port);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ThreadManager.getShortPool().execute(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					if(connect){
						try {
							System.out.println("���������ڵȴ��ͻ�������...");
							Socket socket = server.accept();
							System.out.println("����һ���ͻ���");
							ClientSocket.addSocket(socket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
	}

}
