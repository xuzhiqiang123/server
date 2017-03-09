package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientSocket {
	
	public static List<Socket> sockets;
	
	public static void addSocket(Socket socket){
		if (sockets == null) {
			sockets = new ArrayList<>();
		}
		sockets.add(socket);
	}

}
