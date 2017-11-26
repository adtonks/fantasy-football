package network.server;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadingThread extends Thread{
	private BufferedReader incomingText;
	private ClientThread clientThread;
	private ObjectInputStream or;
	
	public ReadingThread(BufferedReader br, ClientThread cl)
	{
		this.incomingText = br;
		this.clientThread = cl;
		
	}

	@Override
	public void run()
	{
		String inc = "";
		try {

			while((inc = this.incomingText.readLine()) != null)
			{
				System.out.println("I received the following message: " + inc);
				// step 1: send a confirmation that text was received
				this.clientThread.sendConfirmation();
				// step 2: broadcast said text to everyone but the sender
				this.clientThread.broadcastMessageToOtherClients(inc);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Connection was closed!");

	}
}
