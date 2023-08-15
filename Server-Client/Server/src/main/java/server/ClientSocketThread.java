package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocketThread implements Runnable {
	
	private final Socket socket;
	private final DataInputStream dis;
	private final DataOutputStream dos;
	 
	public ClientSocketThread(Socket socket) throws IOException {
		this.socket = socket;
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		
		while(true) {
			try {
				String clientMessage = this.dis.readUTF();
			} catch (IOException e) {
				
				throw new RuntimeException(e);
			}
		}
				
	}
	
	public void sendMessage(String message) throws IOException {
		
		this.dos.writeUTF(message);
		this.dos.flush();
	}

}
