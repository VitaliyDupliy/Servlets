package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) throws IOException {
		
		try (Scanner scanner = new Scanner(System.in);
				Socket socket = new Socket("localhost", 8080)) {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); 
			
			Thread fsThread = new Thread(new FromServerThread(dis));
			fsThread.setDaemon(true);
			fsThread.start();
			
			 while(true) {
				 
				 String text = scanner.nextLine();
				 
				 if("exit".equals(text)) break;
				 
				 dos.writeUTF(text);
			 }
		}
		
		
	}
	
	private static class FromServerThread implements Runnable {
		
		private final DataInputStream dis;
		
		private FromServerThread (DataInputStream dis) {
			this.dis = dis;
		}
		@Override
		public void run() {
			
			try {
				while(true) {
					String serverResponse = dis.readUTF();
					System.out.println("Server: " + serverResponse);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
