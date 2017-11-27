package network.client;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClient client = new MyClient("hostname", 3820);
		client.sendTextToServer("Hello");
	}

}
