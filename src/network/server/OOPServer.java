package network.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class OOPServer {
	private ServerSocket servSock;
	private Socket client;
	private PrintWriter pw;

	public OOPServer(int port)
	{
		try {
			this.servSock = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void acceptClientLoop()
	{
		while (true)
		{
			Socket c;
			try {
				c = this.servSock.accept();
				ClientThread th = new ClientThread(c, this);
				th.start();
				ReadingThreat rh = new ReadingThread(null, th);
				System.out.println("Just accepted a client. Going to the next iteration");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

/*


	public void acceptOneClient() throws Exceptio

	{

		System.out.println("Waiting for an incoming connection");
		this.client = this.servSock.accept();
		System.out.println("I accepted a connection!");
		this.pw = new PrintWriter(client.getOutputStream());

		pw.println("Hi!");
		pw.flush();
	}

	public void cleanStreams() throws Exception
	{
		System.out.println("Closing streams and cleaning stuff");
		pw.close();
		client.close();
		this.servSock.close();
	}

*/
	public static void main (String[] args) throws Exception
	{
		OOPServer serv = new OOPServer(1664);

		serv.acceptClientLoop();
	}

	public void broadcastMessage(String message, ClientThread sender) {
//		for each client but the sender, send message
		
	}
}
