package network.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket servSock;
	private Socket client;
	private PrintWriter pw;
	
	public static void main(String[] args){
		Server serv = new Server(3820);

		serv.acceptClientLoop();
	}
	
	public Server(int port) {
		try {
			this.servSock = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void acceptClientLoop() {
		while (true) {
			Socket c;
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
