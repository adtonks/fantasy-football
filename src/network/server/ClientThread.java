package network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	private Socket client;
	private PrintWriter pw;
	private BufferedReader br;
	private ObjectOutputStream ow;

	private Server server;

	public ClientThread(Socket c, Server Server) {
		this.server = Server;
		this.client = c;
		try {
			this.pw = new PrintWriter(this.client.getOutputStream());
			this.br = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			this.ow = new ObjectOutputStream(this.client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendMessage(String message) {
		this.pw.println(message);
		this.pw.flush();
	}

	public String readTextFromClient() {

		String inc = "";
		try {
			while ((inc = this.br.readLine()) != null) {
				System.out.println("Server: Waiting for input");
				return inc;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void run() {

		System.out.println("Server: Client connected");
		String message = this.readTextFromClient();
		System.out.println(message);
		this.sendMessage("You said " + message);
	
		
		System.out.println("Server: client was disconnected");
	

	}

	public void cleanConnection() {
		System.out.println("Server: client disconnecting, cleaning the data!");
		this.pw.close();
		try {
			this.br.close();
			this.client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
