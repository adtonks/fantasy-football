package network.server;

import java.io.IOException;

public class Server{

	public static void main(String[] args) throws IOException{
		MyServer serv = new MyServer(8020);

		serv.acceptClientLoop();

	}

}
