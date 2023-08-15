package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static void main(String[] args) throws IOException {
		
		final List<ClientSocketThread> connectedClients = new ArrayList<>();
		
		try (ServerSocket serverSocket = new ServerSocket(8080)) { 
			
			while(true) {
				Socket socket = serverSocket.accept();
				ClientSocketThread connectedClient = new ClientSocketThread(socket);
				
				Thread clientThread = new Thread(connectedClient);
				clientThread.setDaemon(true);
				clientThread.start();
				
				connectedClients.add(connectedClient);
			}
		} 

	}

}
