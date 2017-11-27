package network.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket servSock;
	private Socket client;
	private PrintWriter pw;
	

	
	public Server(int port) {
		try {
			this.servSock = new ServerSocket(port);
			this.servSock.setReuseAddress(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void acceptClientLoop() throws IOException {
		boolean end = true;
		while (end) {
			Socket c = null;
			try {
				c = this.servSock.accept();
				ClientThread th = new ClientThread(c, this);
				th.start();
			} catch (IOException e) {
				e.printStackTrace();
			}

		
		}
	}


}
