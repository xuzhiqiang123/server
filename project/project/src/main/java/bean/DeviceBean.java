package bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import server.ClientPushSocket;
import server.ClientSocket;
import util.ThreadManager;

public class DeviceBean {
	
	private Socket socket;
	private String name;
	private Runnable task;
	private OutputStream outputStream;
	
	public DeviceBean(Socket socket) {
		this.socket = socket;
		run();
	}
	
	public void run(){
		if (socket != null && !socket.isClosed()) {
			task = new Runnable() {
				
				@Override
				public void run() {
					try {
						outputStream = socket.getOutputStream();
						InputStream stream = socket.getInputStream();
						handleStream(stream);
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("22222");
					}
				}
			};
			ThreadManager.getLongPool().execute(task);
		}
	}
	
	private void handleStream(InputStream stream){
		try {
			int recCount = 0;
			byte[] data = new byte[1024];
			while((recCount = stream.read(data)) > 0){
				System.out.println("socket receive data is:"+new String(data,0,recCount,"utf-8"));
				ClientPushSocket.sendTestData(new String(data, 0, recCount));
			}
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("111111");
			ClientSocket.removeSocket(socket);
		}
	}
	
	public void writeData(int d){
		if(outputStream != null){
			try {
				outputStream.write(d);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("write to socket failure!!!");
			}
		}
	}
	
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Runnable getTask() {
		return task;
	}
	public void setTask(Runnable task) {
		this.task = task;
	}
	
	
}
