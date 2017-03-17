package test;

import java.io.IOException;
import java.net.ServerSocket;

public class Demo2 {
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(30003);
		while(true){
			try {
				System.out.println("========");
				java.net.Socket s = serverSocket.accept();
				System.out.println("------");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
