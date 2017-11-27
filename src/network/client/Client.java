package network.client;

public class Client {
	MyClient myClient;
		
	public Client(String _IP, int _port) {
		this.myClient = new MyClient(_IP, _port);
	}
	
	public String request(String request) {
		myClient.sendTextToServer(request);
		return(myClient.readTextFromServer());
	}

}
