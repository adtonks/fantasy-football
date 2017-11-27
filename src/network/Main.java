package network;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClient client = new MyClient("127.0.0.1", 8020);
		client.sendTextToServer("Hello");
		String message = client.readTextFromServer();
		client.sendTextToServer("Sending information back:" + message);
		
	}

}
