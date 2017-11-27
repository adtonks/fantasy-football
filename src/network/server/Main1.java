package network.server;

import java.io.IOException;

public class Main1{

	public static void main(String[] args) throws IOException{
		Server serv = new Server(8010);

		serv.acceptClientLoop();

	}

}
