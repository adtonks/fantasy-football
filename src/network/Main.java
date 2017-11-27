package network;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClient client = new MyClient("127.0.0.1", 8010);
		client.sendTextToServer("Hello");
		System.out.println("Client recieved " + client.readTextFromServer());
		
	}

}
