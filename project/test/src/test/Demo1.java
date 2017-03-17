package test;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Demo1 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in); 
		try {
			Socket socket = new Socket("192.168.121.115", 30000);
//			Socket socket = new Socket("139.199.221.66", 8233);
			System.out.println(socket.isConnected());
			System.out.println("----------------");
			OutputStream os = socket.getOutputStream();
			while(true){
				String line = s.nextLine();
				os.write(line.getBytes("utf-8"));
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}
}
