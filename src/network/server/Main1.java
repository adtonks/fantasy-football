package network.server;

public class Main1{

	public static void main(String[] args){
		Server serv = new Server(3820);

		serv.acceptClientLoop();
	}

}
