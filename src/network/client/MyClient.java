package network.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {
	private String ip;
	private int port;
	private Socket connection;
	private BufferedReader br;
	private PrintWriter pw;
	private ObjectOutputStream ow;
	private ObjectInputStream or;

	public MyClient(String ip, int p) {
		this.ip = ip;
		this.port = p;

		try {
			this.connection = new Socket(this.ip, this.port);
			this.br = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
			this.pw = new PrintWriter(this.connection.getOutputStream());
			this.ow = new ObjectOutputStream(this.connection.getOutputStream());
			this.or = new ObjectInputStream(this.connection.getInputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Object readObjectFromServer() {

		Object obj = null; 
		try {

			while ((obj = this.or.readObject()) != null) {
				return obj;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public String readTextFromServer() {
	
			String inc = "";
			try {
				while ((inc = this.br.readLine()) != null) {
					return inc;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}

	public void sendTextToServer(String text) {
		this.pw.println(text);
		this.pw.flush();
		this.closeConnection();
	}

	public void sendObjectToServer(Object obj) {
		try {
			this.ow.writeObject(obj);

			this.ow.flush();
			this.closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {

		try {
			this.pw.close();
			this.br.close();
			this.ow.close();
			this.connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


}
